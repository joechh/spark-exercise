package Spark_codeFight

import Spark_codeFight.Godutch.{inputA, inputB}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/26.
  */
object PickUpPeanuts extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext

  val input: RDD[(String, List[Int])] = sc.parallelize(List(("Ryan", List(1, 3, 5, 7, 9)), ("IFeng", List(2, 4, 6, 8, 10))))
  val threshold: Int = 3

  PickUpPeanuts_Solution.answer(input, threshold).foreach(println)


  //val output: RDD[(String, List[Int])] = sc.parallelize(List(("Ryan", List(5, 7, 9)), ("IFeng", List(4, 6, 8, 10))))

}

object PickUpPeanuts_Solution {
  def answer(inputRDD: RDD[(String, List[Int])], threshold: Int): RDD[(String, List[Int])] = {
    /** Write your code here. */

    val res = inputRDD.map(x => {
      val list = x._2.filter(_ > threshold)
      (x._1, list.sortWith(_ < _))
    })
    res
  }
}