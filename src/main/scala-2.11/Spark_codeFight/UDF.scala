package Spark_codeFight

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/26.
  */
object UDF extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext;
  val input: RDD[(String, Int)] = sc.parallelize(List(("apple", 5)))
  val res=UDF_Solution.answer(input, UDF_Solution.func)
  res.collect().foreach(println)



}

object UDF_Solution {
  def answer(data: RDD[(String, Int)], udf: Int => Int): RDD[(String, Int)] = {
    /** Write your code here. */
    return data.map(x => (x._1, udf(x._2)))
  }

  def func(x: Int): Int = {
    /** Write your code here. */
    return x + 1
  }
}