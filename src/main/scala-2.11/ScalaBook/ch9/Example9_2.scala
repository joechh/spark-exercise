package ScalaBook.ch9

/**
  * Created by joechh on 2017/5/21.
  */
object Example9_2 extends App {
  for (i <- 1 to 6)
    i match {
      case 1 => println(1)
      case x if (x % 2 == 0) => println(s"$x is divisible by 2")
      case _ => Unit
    }

}
