package Spark_codeFight

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/30.
  */
object HowMuchIsIt extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext
  val input = sc.parallelize(List(("9/1", "apple"),
    ("9/2", "apple"),
    ("9/2", "banana"),
    ("9/3", "apple"),
    ("9/3", "mac")))
  val mapper: Map[String, Int] = Map(("apple", 5), ("banana", 3), ("mac", 1000))

  HowMuchIsIt_Solution.answer(input, mapper, sc).foreach(println)


}

object HowMuchIsIt_Solution {
  def answer(data: RDD[(String, String)], mapper: Map[String, Int], sc: SparkContext): RDD[(String, Int)] = {
    /** Write your code here. */
    val res=data.map(x=>(x._1,mapper(x._2))).reduceByKey(_+_)
    res

  }

}