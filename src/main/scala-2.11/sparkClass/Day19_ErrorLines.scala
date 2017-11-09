package sparkClass

/**
  * Created by joechh on 2016/12/6.
  */

import org.apache.spark._
import org.apache.spark.streaming._

object Day19_ErrorLines extends App {
  val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
  val ssc = new StreamingContext(conf, Seconds(10)) //can also created from existing sc object
  val lines = ssc.socketTextStream("localhost", 9999)
  val errorLines = lines.filter(_.contains("error"));
  errorLines.print()
  ssc.start()
  ssc.awaitTermination()
}
