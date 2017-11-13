package Spark_codeFight

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/26.
  */
object BoredChild extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext;

  val input: RDD[(String, String)] = sc.parallelize(List(("2", "hello hi how are you")))

  BoredChild_Sol.answer(input).collect.foreach(println)

  val output: RDD[List[(String, String)]] = sc.parallelize(List(List(("2", "hello"), ("2", "hi"), ("2", "how"), ("2", "are"), ("2", "you"))))


}

object BoredChild_Sol {
  def answer(data: RDD[(String, String)]): RDD[List[(String, String)]] = {
    val res=data.map(kv=>{
      for (word<-kv._2.split(" ")) yield (kv._1,word)
    }).map(x=>x.toList)
    res
  }
}