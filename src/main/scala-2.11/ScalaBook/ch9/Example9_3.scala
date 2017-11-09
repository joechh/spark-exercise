package ScalaBook.ch9

/**
  * Created by joechh on 2017/5/21.
  */
object Example9_3 extends App {
  def patternMatching(x: Int) = x match {
    case 5 => "int 5"
    case x if (x % 2 == 0) => "divisible by 2 "
    case _ => "others"
  }

  println(patternMatching(5))
  println(patternMatching(2))
  println(patternMatching(1))
}
