package Spark_codeFight

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.mllib.rdd.RDDFunctions._


/**
  * Created by joechh on 2017/9/26.
  */
object MovingAverage extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext;

  val nums: RDD[Double] = sc.parallelize(List(1, 1, 1, 2, 2, 2, 3, 3, 3).map(_.toDouble))
  val windows = 3
  MovingAverage_Sol.answer(nums, windows).foreach(println)
}


object MovingAverage_Sol {
  def answer(nums: RDD[Double], windows: Int): RDD[Double] = {

    val res = nums.sliding(windows).map(_.sum / windows).map(BigDecimal(_).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble)
    res
  }
}