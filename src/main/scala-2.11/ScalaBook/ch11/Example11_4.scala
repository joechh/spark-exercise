package ScalaBook.ch11

/**
  * Created by joechh on 2017/5/23.
  */
object Example11_4 extends App {

  val arrStr: Array[String] = Array("Hadoop", "Hive", "Spark")
  val arrInt: Array[Int] = Array(1, 2, 3)

  printAll(arrStr)
  printAll(arrInt)

  def printAll(x: Array[_]) = {
    for(i<-x){
      print(i+" ")
    }
    println()
  }

}
