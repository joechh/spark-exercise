package Spark_codeFight

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/30.
  */
object FruitStore extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext
  val input: RDD[(String, List[Double])] = sc.parallelize(List(("apple", List(3, 5)), ("banana", List(5, 5))))

  FruitStore_Solution.answer(input).foreach(println)

  //  val output: RDD[(String, Double)] =
  //    sc.parallelize(List(("apple", 4.0), ("banana", 5.0)))

}

object FruitStore_Solution {
  def answer(data: RDD[(String, List[Double])]): RDD[(String, Double)] = {
    val res = data.map(x => {
      val sum = x._2.sum
      val count = x._2.size
      (x._1, sum / count)
    })

    res
  }
}

