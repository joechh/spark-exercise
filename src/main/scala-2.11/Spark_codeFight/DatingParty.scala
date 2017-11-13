package Spark_codeFight

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/26.
  */
object DatingParty extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext;

  val inputA: RDD[String] = sc.parallelize(List("Ryan", "IFeng", "Larry"))
  val inputB: RDD[String] = sc.parallelize(List("Iris", "Rose", "Lion"))

  DatingParty_Sol.answer(inputA, inputB).collect.foreach(println)

  //val output: RDD[(String, String)] = sc.parallelize(List(("Ryan", "Rose"), ("IFeng", "Iris"), ("Larry", "Lion")))


}

object DatingParty_Sol {
  def answer(inputA: RDD[String], inputB: RDD[String]): RDD[(String, String)] = {
    /** Write your code here. */
    inputA.sortBy(x=>x).zip(inputB.sortBy(x=>x))
  }
}