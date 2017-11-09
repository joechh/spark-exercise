package ScalaBook.ch9

/**
  * Created by joechh on 2017/5/21.
  */
object Example9_1 extends App {
  for (i <- 1 to 5)
    i match {
      case 1=>println(1)
      case 2=>println(2)
      case 3=>println(3)
      case _=>println("others")
    }

}
