package sparkClass

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Row, SparkSession}

/**
  * Created by joechh on 2017/1/25.
  */
object Day31_3_Fuction extends App {
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

  itPostsDF.show(5)

  import spark.implicits._

  itPostsDF.select(year('creationDate)).show(1)

  //sort question by the amount of active time
  itPostsDF.filter('postTypeId === 1)
    .withColumn("activePeriod", datediff('lastActivityDate, 'creationDate))
    .orderBy('activePeriod.desc).show(10)

  //get head
  val head = itPostsDF
    .head.getString(3)
    .replace("&lt;", "<")
    .replace("&gt;", ">")

  print(head)

  //aggregation function
  itPostsDF
    .select(avg('score), max('score), min('score), count('score))
    .show


  //Window functions: ranking/analytic/aggregate
  //Ex1
  val windows1 = itPostsDF.filter('postTypeId === 1).
    select('ownerUserId, 'acceptedAnswerId, 'score, max('score).over(Window.partitionBy('ownerUserId)) as "maxPerUser")
    .withColumn("toMax", 'maxPerUser - 'score).show(10)


  val window2 = itPostsDF.filter('postTypeId === 1)
    .select('ownerUserId, 'id, 'creationDate,
      lag('id, 1).over(Window.partitionBy('ownerUserId).orderBy('creationDate)) as "prev",
      lead('id, 1).over(Window.partitionBy('ownerUserId).orderBy('creationDate)) as "next"
    ).orderBy('ownerUserId, 'id).show(10)

  //UDF

  val countTags: String => Int = {
    tags => "&lt".r.findAllMatchIn(tags).length
  }

  val countTagsUDF = spark.udf.register("countTags", countTags)

  itPostsDF.filter('postTypeId === 1)
    .select('tags, countTagsUDF('tags) as "tagCnt").show(10, false)

  //Miss value handling
  //drop
  val cleanPosts = itPostsDF.na.drop
  println(itPostsDF.count())
  println(cleanPosts.count())
  cleanPosts.show(5)

  //fill(when value is Null or NaN)
  itPostsDF.na.fill(Map("viewCount" -> 0, "answerCount" -> -1)).show(10)

  //replace(change val from A to B)
  itPostsDF.na.replace(Array("id", "acceptedAnsweredId"), Map(1177 -> 3300)).show(3)


}
