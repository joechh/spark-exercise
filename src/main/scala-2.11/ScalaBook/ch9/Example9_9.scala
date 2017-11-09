package ScalaBook.ch9

/**
  * Created by joechh on 2017/5/21.
  */
object Example9_9 extends App {

  val tuple = (1, 2, 3, 4)


  def patternMatching(x: Any) = x match {
    case (first, second) => println(s"first=$first, second=$second")
    case (first, _, third, _) => println(s"first=$first, third=$third")
    //!Error when parsing tuple: case (first,z _*) => println(s"first=$first, third=$third")
    case _ =>
  }

  patternMatching(tuple)
}
