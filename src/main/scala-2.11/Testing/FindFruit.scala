package Testing

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/26.
  */
object FindFruit extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext

  val inputRDD: RDD[(String, String)] = sc.parallelize(List(("apple", "fruit"), ("apple", "fruit"), ("banana", "fruit"), ("mac", "3c")))
  val condition: String = "fruit"

}

object FindFruit_Sol {
  def answer(inputRDD: RDD[(String, String)], condition: String): RDD[String] = {
    val res=inputRDD
      .filter(pair=>pair._2.equals(condition))
      .keys
      .distinct()
    res
  }
}