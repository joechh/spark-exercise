package sparkClass

/**
  * Created by joechh on 2016/12/6.
  */


import java.sql.Timestamp
import java.text.SimpleDateFormat

import org.apache.spark._
import org.apache.spark.streaming._

object Day20_FileStreaming extends App {
  val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
  val ssc = new StreamingContext(conf, Seconds(1))
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

  val numPerType = orders.map(o => (o.buy, 1L)).
    reduceByKey(_ + _)

  numPerType.print()
  ssc.start()
  ssc.awaitTermination()
}


//numPerType.repartition(1).saveAsTextFiles("hdfs://localhost:9000/user/joechh/sparkDir/output/", "txt")

