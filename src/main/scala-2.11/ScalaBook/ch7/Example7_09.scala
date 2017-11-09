package ScalaBook.ch7

import java.io.PrintWriter

/**
  * Created by joechh on 2017/5/21.
  */
object Example7_09 extends App {

  trait Logger {
    println("Logger")

    def log(msg: String): Unit
  }

  trait FileLogger extends Logger {
    lazy val fileOutput = new PrintWriter(fileName: String)
    val fileName: String

    def log(msg: String): Unit = {
      fileOutput.print(msg)
      fileOutput.flush()
    }
  }

  class Person

  class Student extends Person with FileLogger {
    override val fileName: String = "studentLazyFile.log"
  }

  new Student().log("trait demo")


}
