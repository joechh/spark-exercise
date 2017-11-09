package sparkClass

import java.sql.Timestamp

import org.apache.spark.sql.{Row, SparkSession}

/**
  * Created by joechh on 2017/1/17.
  */
object Day30_SparkSQL extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()

  import spark.implicits._

  val itPostsRow = spark.sparkContext.textFile("/home/joechh/Scala/sparkClass/src/main/resources/day31/italianPosts.csv")
  val itPostsSplit = itPostsRow.map(_.split("~"))
  // RDD[Array[String]]
  val itPostsTuple = itPostsSplit.map(x => (x(0), x(1), x(2), x(3), x(4), x(5), x(6), x(7), x(8), x(9), x(10), x(11), x(12)))

  // Sol1, usingToDF(), it will convert RDD to DFrame, but type of columns is all String, in some cases not very precise.
  val itPostsDF = itPostsTuple.toDF("commentCount", "lastActivityDate",
    "ownerUserId", "body", "score", "creationDate", "viewCount", "title",
    "tags", "answerCount", "acceptedAnswerId", "postTypeId", "id")

  itPostsDF.printSchema()
  itPostsDF.show(10)

  // Sol2, using class Class and then use toDF(),
  import StringImplicits._

  def stringToPost(row: String): Post = {
    val r = row.split("~")
    Post(r(0).toIntSafe,
      r(1).toTimestampSafe,
      r(2).toLongSafe,
      r(3),
      r(4).toIntSafe,
      r(5).toTimestampSafe,
      r(6).toIntSafe,
      r(7),
      r(8),
      r(9).toIntSafe,
      r(10).toLongSafe,
      r(11).toLongSafe,
      r(12).toLong
    )
  }

  val itPostsDFCase = itPostsRow.map(x => stringToPost(x)).toDF()

  itPostsDFCase.printSchema()
  itPostsDFCase.show()


  // Sol3, Explicitly create Schema, and apply on RDD[Row] by  spark.createDataFrame
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
  val itPostsDFStruct = spark.createDataFrame(rowRDD, postSchema)

  itPostsDFStruct.columns.foreach(println)
  itPostsDFStruct.dtypes.foreach(println)
  itPostsDFStruct.printSchema()
  itPostsDFStruct.show()

}

case class Post(commentCount: Option[Int], lastActivityDate: Option[java.sql.Timestamp], ownerUserId: Option[Long],
                body: String, score: Option[Int], creationDate: Option[java.sql.Timestamp], viewCount: Option[Int],
                title: String, tags: String, answerCount: Option[Int], acceptedAnswerId: Option[Long],
                postTypeId: Option[Long], id: Long)

object StringImplicits {

  implicit class StringImprovements(val s: String) {

    import scala.util.control.Exception.catching

    def toIntSafe = catching(classOf[NumberFormatException]) opt s.toInt

    def toLongSafe = catching(classOf[NumberFormatException]) opt s.toLong

    def toTimestampSafe = catching(classOf[IllegalArgumentException]) opt Timestamp.valueOf(s)
  }

}
