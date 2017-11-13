package Spark_codeFight

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/30.
  */
object DistributeProperties extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext
  val input: RDD[Int] = sc.parallelize(1 to 500) // Total sheep
  val n: Int = 2 // Your part
  val m: Int = 3 // Her part
  val res:Array[RDD[Int]] = DistributeProperties_Solution.answer(input, n, m)
  res.foreach(x => {
    x.foreach(sheep=>print(sheep+" "))
    println()

  })

  //val output: Array[RDD[Int]] = sc.parallelize(Array((1,2,3,...3xx), (3xx+1,....500)))

}

object DistributeProperties_Solution {
  def answer(data: RDD[Int], n: Int, m: Int): Array[RDD[Int]] = {
    val my = data.count() * n / (n + m)
    val myPart:RDD[Int] = data.filter(_ <= my)
    val herPart:RDD[Int] = data.filter(_ > my)
    Array(myPart,herPart)
  }

}