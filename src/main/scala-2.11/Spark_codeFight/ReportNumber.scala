package Spark_codeFight

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/26.
  */
object ReportNumber extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext;

  val inputA: RDD[String] = sc.parallelize(List("Ryan", "IFeng", "Larry"))


  ReportNumber_Sol.answer(inputA).collect.foreach(println)

  //val output: RDD[(String, String)] = sc.parallelize(List((1, "IFeng"), (2, "Larry"), (3, "Ryan")))


}

object ReportNumber_Sol {
  def answer(input: RDD[String]): RDD[(Long, String)] = {
    val res=input.sortBy(x=>x).zipWithIndex().map(kv=>(kv._2+1,kv._1))
    res
  }
}