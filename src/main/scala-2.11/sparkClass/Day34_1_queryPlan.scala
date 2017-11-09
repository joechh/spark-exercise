package sparkClass

import org.apache.spark.sql.{Row, SparkSession}

/**
  * Created by joechh on 2017/4/12.
  */
object Day34_1_queryPlan {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[*]")
      .appName("Day34_1")
      .enableHiveSupport()
      .getOrCreate()

    val itPostsRow = spark.sparkContext.textFile("/home/joechh/Scala/sparkClass/src/main/resources/day31/italianPosts.csv")
    val itPostsSplit = itPostsRow.map(_.split("~"))
    // RDD[Array[String]]
    val itPostsRDD = itPostsSplit.map(x => (x(0), x(1), x(2), x(3), x(4), x(5), x(6), x(7), x(8), x(9), x(10), x(11), x(12)))

    import org.apache.spark.sql.types._


    val postSchema = StructType(Seq(
      StructField("commentCount", IntegerType, true),
      StructField("lastActivityDate", TimestampType, true),
      StructField("ownerUserId", LongType, true),
      StructField("body", StringType, true),
      StructField("score", IntegerType, true),
      StructField("creationDate", TimestampType, true),
      StructField("viewCount", IntegerType, true),
      StructField("title", StringType, true),
      StructField("tags", StringType, true),
      StructField("answerCount", IntegerType, true),
      StructField("acceptedAnswerId", LongType, true),
      StructField("postTypeId", LongType, true),
      StructField("id", LongType, false))
    )

    import StringImplicits._

    def stringToRow(row: String): Row = {
      val r = row.split("~")
      Row(r(0).toIntSafe.getOrElse(null),
        r(1).toTimestampSafe.getOrElse(null),
        r(2).toLongSafe.getOrElse(null),
        r(3),
        r(4).toIntSafe.getOrElse(null),
        r(5).toTimestampSafe.getOrElse(null),
        r(6).toIntSafe.getOrElse(null),
        r(7),
        r(8),
        r(9).toIntSafe.getOrElse(null),
        r(10).toLongSafe.getOrElse(null),
        r(11).toLongSafe.getOrElse(null),
        r(12).toLong)
    }

    val rowRDD = itPostsRow.map(row => stringToRow(row))
    val postsDF = spark.createDataFrame(rowRDD, postSchema).persist()

    import spark.implicits._
    val postsFiltered = postsDF
      .filter('postTypeId === 1)
      .withColumn("ratio", 'viewCount / 'score)
      .where('ratio < 35)

    postsFiltered.explain(true)


  }
}
