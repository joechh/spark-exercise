package sparkClass

import java.sql.Timestamp

import org.apache.spark.sql.{Row, SparkSession}

/**
  * Created by joechh on 2017/4/12.
  */
object Day33_2_QueryFromHiveMetaStore {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[*]")
      .appName("Day33_3")
      .enableHiveSupport()
      .getOrCreate()

    //Supposedly "posts" and "votes" table have been stored in Hive MetaStore executed in Day33_1_PlainSQL


    spark.catalog.listTables().show
    spark.catalog.listColumns("posts").show()
    val res = spark.sql("select * from votesjson")
    res.show()
    val df2 = spark.read.format("json").load("hdfs://smack02:9000/user/joechh/testTable2")
    df2.show()
  }
}
