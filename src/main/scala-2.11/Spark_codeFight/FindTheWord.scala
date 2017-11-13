package Spark_codeFight

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/26.
  */
object FindTheWord extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext

  val input: RDD[String] = sc.parallelize(Seq("hello hello world"))
  val condition: Int = 2
  val res=FindTheWord_Solution.answer(input,condition)
  res.collect().foreach(println)

}

object FindTheWord_Solution {
  def answer(inputRDD: RDD[String], condition: Int): RDD[String] = {
   val res=inputRDD
     .flatMap(_.split(" "))
     .map(x=>(x,1))
     .reduceByKey(_+_)
     .filter(_._2==condition)
     .map(_._1)

    return res
  }
}