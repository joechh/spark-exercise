package Spark_codeFight

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/26.
  */
object BasicTextMining extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext;

  val input: RDD[String] = sc.parallelize(List("hello hello world warld a a c b d A A B C D D D"))
  BasicTextMining_Sol.answer(input).collect.foreach(println)

  //val output: RDD[(String, Int)] = sc.parallelize((("hello", 1), ("world", 1)))


}

object BasicTextMining_Sol {
  def answer(rdd: RDD[String]): RDD[(String, Int)] = {
    /** Write your code here. */

    val groupByCount = rdd.flatMap(_.split(" ")).map(x => (x, 1)).reduceByKey(_ + _)
    val sort = groupByCount.sortBy(kv => (-kv._2, kv._1))
    sort


  }
}