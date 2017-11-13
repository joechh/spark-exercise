package Spark_codeFight

import Spark_codeFight.Godutch.{inputA, inputB}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/26.
  */
object HaveFreestyle extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext

  val input: RDD[(String, List[String])] = sc.parallelize(List(("9/30", List("egg", "steak"))))

  HaveFreestyle_Solution.answer(input, HaveFreestyle_Solution.func).foreach(println)


  //val output: RDD[(String, Int)] = sc.parallelize(List(("9/30", 2)))

}

object HaveFreestyle_Solution {
  def answer(data: RDD[(String, List[String])],
             udf: List[String] => Int): RDD[(String, Int)] = {
    val res=data.map(x=>(x._1,udf(x._2)))
    res
  }
  def func(x: List[String]): Int = {


    x.distinct.size
  }
}