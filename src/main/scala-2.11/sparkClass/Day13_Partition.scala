package sparkClass

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
  * Created by joechh on 2016/12/27.
  */
object Day13_Partition extends App {
  val conf = new SparkConf()
    .setAppName("testApp")
    .setMaster("local[*]")
  val sc = new SparkContext(conf)
  val list = sc.parallelize(List((1, 1), (1, 2), (1, 3), (2, 1), (1, 2)))

  val testP1=list.foldByKey(0,100)(_ + _)
  val testP2=list.foldByKey(0,new HashPartitioner(100))(_ + _)
  println(testP1.partitions.size)
  println(testP2.partitions.size)



}
