package ScalaBook.ch9

/**
  * Created by joechh on 2017/5/21.
  */
object Example9_11 extends App {

  val list = List(List(1, 2, 3, 4), List(4, 5, 6))


  def patternMatching(x: Any) = x match {
    case d1@List(_, d2@List(4, _*)) => println("d1:" + d1 + "\n" + "d2:" + d2)
    case _ =>
  }


  patternMatching(list)

}
