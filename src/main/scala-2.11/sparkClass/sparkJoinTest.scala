package sparkClass

import org.apache.spark.sql.{Row, SparkSession}

/**
  * Created by joechh on 2017/6/12.
  */
object sparkJoinTest {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("testJoin")
      .master("local[2]")
      .getOrCreate();

    import spark.implicits._


    val df1 = List(("Happy" -> 1.0), ("Sad" -> 0.9), ("Happy" -> 1.5), ("Coffee" -> 3.0)).toDF("Name", "Size")
    val df2 = spark.sparkContext.parallelize(List(("Happy" -> "94110"), ("Happy" -> "94103"), ("Coffee" -> "10504"), ("Tea" -> "07012"))).toDF("Name", "Zip")

    df1.show()
    df2.show()

    //Inner Jon
    df1.join(df2, df1("name") === df2("name")).show()
    df1.join(df2, df1("name") === df2("name"), "inner").show()

    //Left
    df1.join(df2, df1("name") === df2("name"), "left").show()

    //Right
    df1.join(df2, df1("name") === df2("name"), "right").show()

    //Full Outer Join
    df1.join(df2, df1("name") === df2("name"), "full_outer").show()

    //Left semi join
    df1.join(df2, df1("name") === df2("name"), "left_semi").show()

    //Left anti join
    df1.join(df2, df1("name") === df2("name"), "left_anti").show()

    //Self join
    df1.as("a").join(df1.as("b")).where($"a.name" === $"b.name").show

    println(df1.as("a").join(df1.as("b")).where($"a.name" === $"b.name").queryExecution)
    println("####")
    println(df1.as("a").join(df1.as("b")).where($"a.name" === $"b.name").queryExecution.executedPlan)

    //val ds1 = df1.as[(String, Double)]
    val ds1 = df1.as[MoodScore]
    val ds2 = df2.as[MoodValue] //Column name of DataFrame and DataSet should be the same!

    ds1.show()
    ds2.show()

    //Join two dataset

    ds1.joinWith(ds2, ds1("Name") === ds2("Name")).show
    ds1.joinWith(ds2, ds1("Name") === ds2("Name"),"left").show


  }

}

case class MoodScore(Name: String, Size: Double)

case class MoodValue(Name: String, Zip: String)



