package sparkClass

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

import scala.io.Source


/**
  * Created by joechh on 2016/12/20.
  */
object Day07_SubmitFormat {
  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.WARN)

    val spark = SparkSession.builder().getOrCreate()
    val sc = spark.sparkContext

    val githubLog = spark.read.json(args(0))
    val pushes = githubLog.filter("type ='PushEvent'")
    val grouped = pushes.groupBy("actor.login").count()
    val ordered = grouped.orderBy(grouped("count").desc)

    val employees = Set() ++ (
      for {
        line <- Source.fromFile(args(1)).getLines()
      } yield line.trim
      )


    val bcEmployees = sc.broadcast(employees)
    import spark.implicits._
    val isEmp: String => Boolean = (name => bcEmployees.value.contains(name))
    val isEmployee = spark.udf.register("isEmpUdf", isEmp)
    val filtered = ordered.filter(isEmployee($"login"))
    filtered.write.format(args(3)).save(args(2))


  }

}

