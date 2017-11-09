package Testing

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/26.
  */
object HelloWorld extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext;

  val input: RDD[String] = sc.parallelize(List("hello world"))
  HelloWorld_Sol.answer(input)


}

object HelloWorld_Sol {
  def answer(rdd: RDD[String]): RDD[(String,Int)] = {
    /** Write your code here. */
    val output=rdd
      .flatMap(_.split(" "))
      .map(x=>(x,1))
      .reduceByKey(_+_)

    output
  }
}