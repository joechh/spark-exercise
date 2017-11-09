package sparkClass

import java.sql.Timestamp

import org.apache.spark.sql.{Row, SparkSession}

/**
  * Created by joechh on 2017/4/12.
  */
object Day32_3_Join {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[*]")
      .appName("Day32_3_Join")
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


    val itVotesRaw = spark.sparkContext.textFile("/home/joechh/Scala/sparkClass/src/main/resources/day31/italianVotes.csv").map(_.split("~"))
    val itVotesRaws = itVotesRaw.map(row => Row(row(0).toLong, row(1).toLong, row(2).toInt, Timestamp.valueOf(row(3))))
    val votesSchema = StructType(Seq(
      StructField("id", LongType, false),
      StructField("postId", LongType, false),
      StructField("voteTypeId", IntegerType, false),
      StructField("creationDate", TimestampType, false)
    ))
    val votesDF = spark.createDataFrame(itVotesRaws, votesSchema)

    val postsVotes = itPostsDF.join(votesDF, itPostsDF("id") === votesDF("postId"), "outer")

    postsVotes.show(5)
    itPostsDF.join(votesDF, itPostsDF("id") === votesDF("postId")).show(5)


  }

}
