package Spark_codeFight

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/30.
  */
object Godutch extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext
  val inputA = sc.parallelize(List(("fruit", "apple"),
    ("fruit", "apple"),
    ("fruit", "banana"),
    ("3c", "mac")))
  val inputB = sc.parallelize(List(("apple", 5), ("banana", 3), ("kiwi", 10)))

  Godutch_Solution.answer(inputA, inputB).foreach(println)

  //  val output: RDD[(String, (String, Option[Int]))] = sc.parallelize(
  //    List(("apple", ("fruit", Some(5))),
  //      ("apple", ("fruit", Some(5))),
  //      ("banana", ("fruit", Some(3))),
  //      ("mac", ("3c", None))))

}

object Godutch_Solution {
  def answer(inputA: RDD[(String, String)],
             inputB: RDD[(String, Int)]): RDD[(String, (String, Option[Int]))] = {
    /** Write your code here. */
    val res = inputA.map(_.swap).leftOuterJoin(inputB)
    res
  }
}

