package Testing

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/26.
  */
object MergeInToOne extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext

  val inputA = sc.parallelize(List(("fruit", "orange"),
    ("fruit", "orange"),
    ("fruit", "banana"),
    ("3c", "mac")))

  val inputB = sc.parallelize(List(("orange", 5),
    ("banana", 3),
    ("kiwi", 10)))

  val res=MergeInToOne_Solution.answer(inputA,inputB)
  res.collect().foreach(println)

}

object MergeInToOne_Solution {
  def answer(inputA: RDD[(String, String)],
             inputB: RDD[(String, Int)]): RDD[(String, (String, Int))] = {
    /** Write your code here. */
    val res=inputA.map(_.swap).join(inputB)
    res
  }
}