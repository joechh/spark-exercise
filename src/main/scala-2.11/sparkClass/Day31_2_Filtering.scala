package sparkClass

import org.apache.spark.sql.{Row, SparkSession}


/**
  * Created by joechh on 2017/1/24.
  */
object Day31_2_Filtering extends App {
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

  import spark.implicits._

  val rowRDD = itPostsRow.map(row => stringToRow(row))
  val itPostsDF = spark.createDataFrame(rowRDD, postSchema)

  val postIdBody = itPostsDF.select(itPostsDF.col("id"), itPostsDF.col("body"))

  println(postIdBody.filter(postIdBody.col("body").contains("Italiano")).count())
  println(postIdBody.filter('body contains "Italiano").count())

  val noAnswer = itPostsDF.filter(('postTypeId === 1) && ('acceptedAnswerId isNull))
  noAnswer.show()

  val firstTenQs = itPostsDF.filter('postTypeId === 1).withColumnRenamed("title", "TITLE") limit (3)
  firstTenQs.show()

  //Create new Column and filtering with new one
  itPostsDF.filter('postTypeId === 1)
    .select(itPostsDF.col("id"), itPostsDF.col("body"),'viewCount,'score)
    .withColumn("ratio", 'viewCount / 'score)
    .where('ratio < 35)
    .show(20)

  //Sorting
  itPostsDF.select('creationDate, 'title)
    .orderBy('creationDate desc)
    .limit(10)
    .show()


}
