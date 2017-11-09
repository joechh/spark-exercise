package sparkClass

import org.apache.spark.sql.SparkSession

import scala.io.Source


/**
  * Created by joechh on 2016/12/20.
  */
object Day07_Brodcast {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("GitHub push counter")
      .master("local[*]")
      .getOrCreate()

    val inputPath = "/home/joechh/Scala/sparkClass/src/main/resources/day05/2015-03-01-0.json"
    val githubLog = spark.read.json(inputPath)
    val pushes = githubLog.filter("type ='PushEvent'")
    import spark.implicits._
    val grouped = pushes.groupBy("actor.login").count()
    val ordered = grouped.orderBy(grouped("count").desc)
    val empPath = "/home/joechh/Scala/sparkClass/src/main/resources/day06/ghEmployees.txt"
    val employees = Set() ++ (
      for {
        line <- Source.fromFile(empPath).getLines()
      } yield line.trim
      )

    val sc = spark.sparkContext
    val bcEmployees = sc.broadcast(employees)
    //val isEmp: String => Boolean = (name => bcEmployees.value.contains(name))
    val isEmp= bcEmployees.value.contains(_)

    val isEmployee = spark.udf.register("isEmpUdf", isEmp)
    val filtered = ordered.filter(isEmployee($"login"))

    filtered.show(5)

  }

}

