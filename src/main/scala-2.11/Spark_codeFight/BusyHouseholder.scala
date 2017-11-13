package Spark_codeFight

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/30.
  */
object ABusyHouseholder extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext
  val inputA: RDD[(String, String)] = sc.parallelize(List(("9/1", "banana")))
  val inputB: RDD[(String, String)] = sc.parallelize(List(("9/2", "apple"), ("9/2", "banana")))
  val inputC: RDD[(String, String)] = sc.parallelize(List(("9/3", "apple"), ("9/3", "mac")))

  ABusyHouseholder_Sol.answer(sc,inputA, inputB, inputC).foreach(println)


}

object ABusyHouseholder_Sol {
  def answer(sc: SparkContext,
             inputA: RDD[(String, String)],
             inputB: RDD[(String, String)],
             inputC: RDD[(String, String)]): RDD[(String, Int)] = {
    /** Write your code here. */
    val unionRDD=inputA.union(inputB).union(inputC)
    val result=unionRDD.map(x=>x.swap).map(x=>(x._1,1)).reduceByKey(_+_)
    result
  }

}