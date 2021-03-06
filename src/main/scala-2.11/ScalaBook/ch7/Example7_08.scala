package ScalaBook.ch7

import java.io.PrintWriter

/**
  * Created by joechh on 2017/5/21.
  */
object Example7_08 extends App {

  trait Logger {
    println("Logger")

    def log(msg: String): Unit
  }

  trait FileLogger extends Logger {
    val fileOutput = new PrintWriter(fileName: String)
    val fileName: String
    fileOutput.println("#")

    def log(msg: String): Unit = {
      fileOutput.print(msg)
      fileOutput.flush()
    }
  }

  class Person
  class Student extends Person with FileLogger{
    override val fileName: String = "studentFile.log"
  }

  val s = new {
    override  val fileName="studentFile.log"
  } with  Student

  s.log("trait demo")

}
