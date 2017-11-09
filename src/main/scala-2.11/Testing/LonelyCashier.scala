package Testing

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/26.
  */
object LonelyCashier extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext
  val input: RDD[(String, Int, Int)] = sc.parallelize(List(("apple", 10, 3), ("banana", 5, 10), ("mac", 10000, 5)))


}

object LonelyCashier_Solution {
  def answer(inputRDD: RDD[(String, Int, Int)]): Int = {
    val res=inputRDD.map(x=>x._2*x._3).reduce(_+_)
    res
  }
}