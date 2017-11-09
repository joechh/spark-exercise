package sparkClass

/**
  * Created by joechh on 2016/12/6.
  */

import org.apache.spark._
import org.apache.spark.streaming._

object Day19_SimpleStreaming extends App {
  val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
  val ssc = new StreamingContext(conf, Seconds(5))
  val lines = ssc.socketTextStream("localhost", 9999)
  val words = lines.flatMap(_.split(" "))
  val pairs = words.map(word => (word, 1))
  val wordCounts = pairs.reduceByKey(_ + _)

  wordCounts.print()
  ssc.start()
  ssc.awaitTermination()
  //In another terminator, using #nc -lk 9999 to try the task!
}
