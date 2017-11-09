package sparkClass

/**
  * Created by joechh on 2016/12/6.
  */

import java.sql.Timestamp
import java.text.SimpleDateFormat

import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import kafka.serializer.StringDecoder
import org.apache.spark.streaming._


object Day26_Kafka extends App {
  val conf = new SparkConf().setMaster("local[2]").setAppName("KafkaStraming")
  val ssc = new StreamingContext(conf, Seconds(10))

  val kafkaReceiverParams = Map[String, String]("metadata.broker.list" -> "192.168.56.1:9092")
  val kafkaStream = KafkaUtils.
    createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaReceiverParams, Set("orders"))



  val orders = kafkaStream.flatMap(line => {
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
    val words = line._2.split(",")
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

  val updatePerClient = (clientId: Long, amount: Option[Double], state: State[Double]) => {
    var total = amount.getOrElse(0.toDouble)
    if (state.exists())
      total += state.get()
    state.update(total)
    Some((clientId, total))

  }
  val amountState = amountPerClient.mapWithState(StateSpec.function(updatePerClient)).stateSnapshots()


  val numPerType = orders.map(o => (o.buy, 1L)).
    reduceByKey(_ + _)

  val buySellList = numPerType.map(t =>
    if (t._1) ("BUYS", List(t._2.toString))
    else ("SELLS", List(t._2.toString))
  )

  val top5clients = amountState.
    transform(_.sortBy(_._2, false).
      zipWithIndex().
      filter(_._2 < 5)).
    map(_._1)

  val top5clList = top5clients.repartition(1).
    map(_._1.toString).
    glom.
    map(arr => ("TOP5CLIENTS", arr.toList))

  val stocksPerWindow = orders.map(x => (x.symbol, x.amount)).
    window(Minutes(60)).
    reduceByKey(_ + _)

  val topStocks = orders.map(x => (x.symbol, x.amount)).
    reduceByKeyAndWindow((_ + _), (_ - _), Minutes(60)).
    transform(_.sortBy(_._2, false).
      zipWithIndex.
      filter(_._2 < 5)).
    map(_._1).
    repartition(1).
    map(_._1.toString).
    glom.
    map(arr => ("TOP5STOCKS", arr.toList))


  val finalStream = buySellList.union(top5clList).union(topStocks)
  ssc.sparkContext.setCheckpointDir("hdfs://localhost:9000/user/joechh/checkPoint")


  finalStream.foreachRDD((rdd) => {
    rdd.foreachPartition((iter) => {
      KafkaProducerWrapper.brokerList = "192.168.56.1:9092"
      val producer = KafkaProducerWrapper.getInstance
      iter.foreach(
        {
          case (k, v) => producer.send("metrics", k+", "+ v.toString)
        }

      )
    }
    )
  }
  )


  ssc.start
  ssc.awaitTermination
}

