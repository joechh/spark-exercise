package ScalaBook.ch9

/**
  * Created by joechh on 2017/5/21.
  */
object Example9_8 extends App {

  val arrInt = Array(1, 2, 3, 4)


  def patternMatching(x: Any) = x match {
    case Array(first, second) => println(s"first=$first, second=$second")
    case Array(first, _, third, _*) => println(s"first=$first, third=$third")
    case _ =>
  }

  patternMatching(arrInt)
}
