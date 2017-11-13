package Spark_codeFight

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/30.
  */
object HardWorkClerk extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext
  val input: RDD[(String, String)] = sc.parallelize(List(("apple", "fruit"), ("apple", "fruit"), ("banana", "fruit"),
    ("mac", "3c"), ("ipad", "3c"), ("ipad", "3c"), ("ipad", "3c")))

  HardWorkClerk_Solution.answer(input).foreach(println)
  //val output: RDD[(String, String, Int)] = sc.parallelize(List(('3c', 'ipad', 3), ('3c', 'mac', 1), ('fruit', 'apple', 2), ('fruit', 'banana', 1)))

}


object HardWorkClerk_Solution {
  def answer(data: RDD[(String, String)]): RDD[(String, String, Int)] = {
    /** Write your code here. */
    val res=data.map(l=>(l,1)).reduceByKey(_+_).map(x=>(x._1._2,x._1._1,x._2))
    res
  }
}


