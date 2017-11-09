package sparkClass

import org.apache.spark.sql.SparkSession

import scala.io.Source

/**
  * Created by joechh on 2016/12/20.
  */
object Day06_For_Set_UDF {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("GitHub push counter")
      .master("local[*]")
      .getOrCreate()

    val homeDir = System.getenv("HOME")
    val inputPath = homeDir + "/Scala/sparkIronMan/Spark/src/main/resources/day06/2015-03-01-0.json"
    val githubLog = spark.read.json(inputPath)
    val pushes = githubLog.filter("type ='PushEvent'")

    val grouped = pushes.groupBy("actor.login").count()
    val ordered = grouped.orderBy(grouped("count").desc)

    ordered.show(5)

    val empPath = homeDir + "/Scala/sparkIronMan/Spark/src/main/resources/day06/ghEmployees.txt"
    val employees = Set() ++ (
      for {
        line <- Source.fromFile(empPath).getLines
      } yield line.trim
      )

    val isEmp: String => Boolean = {
      name => employees.contains(name)
    }



    import spark.implicits._
    val isEmployee = spark.udf.register("isEmpUdf", isEmp)

    val filtered = ordered.filter(isEmployee($"login"))
    filtered.show(5)


  }

}
