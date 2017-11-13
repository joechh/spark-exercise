package Spark_codeFight

import Spark_codeFight.Godutch.{inputA, inputB}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/26.
  */
object HappyTrip extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext

  val input: RDD[(String, (String, Int))] =
    sc.parallelize(List(("Some1", ("ABC", 9989)),
      ("Some2", ("XYZ", 235)),
      ("Some3", ("BBB", 5379)),
      ("Some4", ("ABC", 5379))))
  val condition: String = "ABC"

  HappyTrip_Solution.answer(input, condition).foreach(println)


//  val output: RDD[(String, (String, Int))] =
//    sc.parallelize(List(("Some1", ("ABC", 9989)), ("Some4", ("ABC", 5379))))

}

object HappyTrip_Solution {
  def answer(inputRDD: RDD[(String, (String, Int))], condition: String): RDD[(String, (String, Int))] = {
    val res=inputRDD.filter(x=>x._2._1.equals(condition))

    res
  }
}