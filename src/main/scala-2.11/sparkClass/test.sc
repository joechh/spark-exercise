import org.apache.spark.{SparkConf,SparkContext}
val conf = new SparkConf()
conf.setMaster("local[*]")
conf.setAppName("Test")
val sc=new SparkContext(conf)

val readMePath = "/opt/spark-2.1.2-bin-hadoop2.7/README.md"
val readme=sc.textFile(readMePath).cache()
readme.foreach(println)
print(readme.count())
print(readme.count())