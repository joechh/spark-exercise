import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

val conf = new SparkConf()
conf.setMaster("local[*]")
conf.setAppName("Test")
val sc = new SparkContext(conf)

val inputA: RDD[(String, String)] = sc.parallelize(List(("9/1", "banana")))
val inputB: RDD[(String, String)] = sc.parallelize(List(("9/2", "apple"), ("9/2", "banana")))
val inputC: RDD[(String, String)] = sc.parallelize(List(("9/3", "apple"), ("9/3", "mac")))
val res=inputA.union(inputB).union(inputC)
res.foreach(println)
res.map(x=>x.swap).foreach(println)
val result=res.map(x=>x.swap).map(x=>(x._1,x._2)).countByKey()
result.foreach(println)


//
//object Solution {
//
//  def answer(sc: SparkContext,
//             inputA: RDD[(String, String)],
//             inputB: RDD[(String, String)],
//             inputC: RDD[(String, String)]): RDD[(String, Int)] = {
//
//
//    return result
//  }
//}
////val output: RDD[(String, Int)] =
////  sc.parallelize(List(("apple", 2), ("banana", 2), ("mac", 1)))
//
//Solution.answer(sc, inputA, inputB, inputC)







