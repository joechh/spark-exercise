package Testing

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/30.
  */
object DistributeProperties {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext
  val input: RDD[Int] = sc.parallelize(1 to 500) // Total sheep
  val n: Int = 2 // Your part
  val m: Int = 3 // Her part

}

object DistributeProperties_Solution {
  def answer(data: RDD[Int], n: Int, m: Int): Array[RDD[Int]] = {
    val indicator = data.map(x => 1).reduce(_ + _)
    val threshold = indicator * (n / (n + m))


    /** Write your code here. */
    return result
  }
}