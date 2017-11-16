import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.rdd.RDDFunctions._

val conf = new SparkConf()
conf.setMaster("local[*]")
conf.setAppName("Test")
val sc = new SparkContext(conf)
//
//val inputA: RDD[(String, String)] = sc.parallelize(List(("9/1", "banana")))
//val inputB: RDD[(String, String)] = sc.parallelize(List(("9/2", "apple"), ("9/2", "banana")))
//val inputC: RDD[(String, String)] = sc.parallelize(List(("9/3", "apple"), ("9/3", "mac")))
//val res=inputA.union(inputB).union(inputC)
//res.foreach(println)
//res.map(x=>x.swap).foreach(println)
//val result=res.map(x=>x.swap).map(x=>(x._1,x._2)).countByKey()
//result.foreach(println)


val list=List(1, 1, 1, 2, 2, 2, 3, 3, 3)
list.sliding(3).map(_.sum.toDouble/3).foreach(println)
  //.map(BigDecimal(_).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble)foreach(println)
//
//BigDecimal(1.23456789).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble


val test = sc.parallelize(List(1, 1, 1, 2, 2, 2, 3, 3, 3))
  .sliding(3).map(_.sum.toDouble/3.0).map(BigDecimal(_).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble)


test.foreach(println)







