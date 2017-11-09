package sparkClass

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.functions._

/**
  * Created by joechh on 2017/4/11.
  */
object Day32_2_Grouping {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("sparkSQL")
      .master("local[2]")
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
    val itPostsDF = spark.createDataFrame(rowRDD, postSchema).persist()

    import spark.implicits._

    //EX1
    itPostsDF.groupBy('ownerUserId, 'tags, 'postTypeId).count.orderBy('count.desc).show(10)

    //EX2, more powerful than EX3
    itPostsDF.groupBy('ownerUserId).agg(max('lastActivityDate), max('score)).show(10)

    //EX3
    itPostsDF.groupBy('ownerUserId).agg(Map("lastActivityDate" -> "max", "score" -> "max")).show(10)

    //EX2+ chain other column expression
    itPostsDF.groupBy(itPostsDF("ownerUserId")).agg(max('lastActivityDate), max('score).gt(5)).show(10)

    //Rollup and Cube

    val smplDF = itPostsDF.where('ownerUserId >= 13 and 'ownerUserId <= 15)

    smplDF.groupBy('ownerUserId, 'tags, 'postTypeId).count().show()
    smplDF.rollup('ownerUserId, 'tags, 'postTypeId).count().show()
    smplDF.rollup('ownerUserId, 'tags, 'postTypeId).count().show()


  }

}
