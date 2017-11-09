package exercise

import org.apache.spark.sql.SparkSession

object FirstApp {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("GitHub push counter")
      .master("local[*]")
      .getOrCreate()
    val homeDir = System.getenv("HOME")
    val inputPath = homeDir + "/sparkClass/src/main/resources/day05/2015-03-01-0.json"
    val githubLog = spark.read.json(inputPath)
    val pushes = githubLog.filter("type ='PushEvent'")
    githubLog.printSchema
    println("all events:" + githubLog.count)
    println("push events:" + pushes.count)
    pushes.show(5)
    val grouped = pushes.groupBy("actor.login").count()
    grouped.show(5)

    val ordered = grouped.orderBy(grouped("count").desc)
    ordered.show(5)
  }
}