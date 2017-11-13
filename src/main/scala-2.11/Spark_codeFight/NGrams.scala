package Spark_codeFight

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * Created by joechh on 2017/9/26.
  */
object NGrams extends App {
  val spark = SparkSession.builder()
    .appName("sparkSQL")
    .master("local[2]")
    .getOrCreate()
  val sc = spark.sparkContext;

  /*Given a input string, N of N-Grams and top frequency topN, return top n N-Grams and its counts.
  You have to do some simple preprocessing:
  1. Transform all characters to lower case.
  2. Keep the hyphen between words, e.g., hard-working.

   */

  val inputStr: String =
    """Spark SQL is a Spark module for structured data processing.
      |Unlike the basic Spark RDD API, the interfaces provided by Spark SQL provide
      |Spark with more information about the structure of both the data and the
      |computation being performed. Internally, Spark SQL uses this extra information
      |to perform extra optimizations. There are several ways to interact with Spark SQL
      |including SQL and the Dataset API. One use of Spark SQL is to execute SQL queries.
      |Spark SQL can also be used to read data from an existing Hive installation.
      |When running SQL from within another programming language the results will
      |be returned as a Dataset/DataFrame. You can also interact with the SQL interface
      |using the command-line or over JDBC/ODBC.""".stripMargin

  val inputRDD: RDD[String] = sc.parallelize(Seq(inputStr))
  val n: Int = 2
  val topN: Int = 5


  NGrams_Sol.answer(inputRDD, n, topN).foreach(println)

  //val output: Set[(String, Int)] = Set(("spark sql", 6), ("interact with", 2), ("and the", 2), ("sql is", 2), ("can also", 2))


}

object NGrams_Sol {
  def answer(input: RDD[String], n: Int, topN: Int): Set[(String, Int)] = {


    val trimStr = input
      .map(x => x.replaceAll("""[,.]""", ""))
      .map(_.toLowerCase)
      .flatMap(words => words.split(" ").sliding(3))


    trimStr.collect.foreach(list => {
      list.foreach(x => print(x + " "))
      println()
    })



    //val ngrams = (for( i <- 1 to n) yield .sliding(i).map(p => p.toList)).flatMap(x => x)
    //ngrams foreach println
    //    val trigrams = trimStr.mapPartitions(_.toList.mkString(" ").sliding(3))
    //    trigrams.foreach(println)

    ???
  }
}