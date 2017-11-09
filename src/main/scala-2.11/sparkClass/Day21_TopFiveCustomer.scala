package sparkClass

/**
  * Created by joechh on 2016/12/6.
  */


import java.sql.Timestamp
import java.text.SimpleDateFormat

import org.apache.spark._
import org.apache.spark.streaming._

object Day21_TopFiveCustomer extends App {
  val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
  val ssc = new StreamingContext(conf, Seconds(10))
  val fileStream = ssc.textFileStream("hdfs://localhost:9000/user/joechh/sparkDir")
  val orders = fileStream.flatMap(line => {
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
    val words = line.split(",")
    try {
      assert(words(6) == "B" || words(6) == "S")
      List(Order(new Timestamp(dateFormat.parse(words(0)).getTime),
        words(1).toLong,
        words(2).toLong,
        words(3),
        words(4).toInt,
        words(5).toDouble,
        words(6) == "B"
      ))
    }
    catch {
      case e: Throwable => println("wrong line format(" + e + "):" + line)
        List()
    }
  })

  val amountPerClient = orders.map(record => (record.clientId, record.amount * record.price))

  def func(vals: Seq[(Double)], preValue: Option[Double]): Option[Double] = preValue match {
    case Some(total) => Some(vals.sum + total)
    case None => Some(vals.sum)
  }


  val amountState = amountPerClient.updateStateByKey(func)
  ssc.sparkContext.setCheckpointDir("/home/joechh/chpoint")

  val top5clients = amountState.
    transform(_.sortBy(_._2, false).zipWithIndex.filter(_._2 < 5)).map(x=>x._1)

  top5clients.print()

  ssc.start()
  ssc.awaitTermination()
}

