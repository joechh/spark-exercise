package sparkClass

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by joechh on 2017/1/9.
  */
object SparkKafka extends App{
  val Array(brokers, topics) = Array("192.168.56.1:9092", "testTopic")

  // Create context with 2 second batch interval
  val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
  val ssc = new StreamingContext(conf, Seconds(2))

  // Create direct kafka stream with brokers and topics
  val topicsSet = topics.split(",").toSet
  val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers)
  val messages = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
    ssc, kafkaParams, topicsSet)

  // Get the lines, split them into words, count the words and print
  val lines = messages.map(_._2)
  val words = lines.flatMap(_.split(" "))
  val wordCounts = words.map(x => (x, 1L)).reduceByKey(_ + _)
  wordCounts.print()

  // Start the computation
  ssc.start()
  ssc.awaitTermination()

}
