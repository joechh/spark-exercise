package sparkClass

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

/**
  * Created by joechh on 2017/4/15.
  */
object PlayforFun {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("test").master("local[2]").getOrCreate()
    val people = List(Person(Some("joe"), Some(32)), Person(Some("doris"), null), Person(null, Some(20)))
    import spark.implicits._
    val peopleDF = spark.sparkContext.parallelize(people).toDF
    peopleDF.show()
    peopleDF.na.fill(-1).show()
    peopleDF.na.fill(Map("name" -> "hahaIamNotNullAnyMore", "age" -> -20)).show()

    val list = List((1, "a"), (2, "b"), (2, "a"))
    val rdd = spark.sparkContext.parallelize(list)
    val ds = rdd.toDF("number", "name").as[Test]
    ds.show(5)


    val res = ds
      .groupByKey(x => x.name)
      .agg(avg('number).as[Double].name("avg")).withColumnRenamed("value","name")
      .as[Result]

    res.show()
    res.printSchema()


  }

}

case class Person(name: Option[String], age: Option[Int])

case class Test(number: Int, name: String)

case class Result(name: String, avg: Double)