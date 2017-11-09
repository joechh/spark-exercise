package ScalaBook.ch7

import java.io.PrintWriter

/**
  * Created by joechh on 2017/5/21.
  */
object Example7_05 extends App {

  trait Logger {
    println("Logger")

    def log(msg: String): Unit
  }

  trait FileLogger extends Logger {
    println("file logger")
    val fileOutput = new PrintWriter("file.log")
    fileOutput.println("#")

    def log(msg: String): Unit = {
      fileOutput.print(msg)
      fileOutput.flush()
    }
  }

  new FileLogger {}.log("trait")


}
