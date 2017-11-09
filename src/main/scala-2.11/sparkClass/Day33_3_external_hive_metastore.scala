package sparkClass

import java.sql.Timestamp

import org.apache.spark.sql.{Row, SparkSession}

/**
  * Created by joechh on 2017/4/12.
  */
object Day33_3_external_hive_metastore {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[*]")
      .appName("Day33_3")
      .enableHiveSupport()
      .getOrCreate()

    //Still does now WORK!!

    val df = spark
      .read
      .option("url", "jdbc:hive2://localhost:10000")
      .option("dbtable", "testtable")
      .option("driver","org.apache.hive.jdbc.HiveDriver")
      .format("jdbc")
      .load
    df.show()


  }
}
