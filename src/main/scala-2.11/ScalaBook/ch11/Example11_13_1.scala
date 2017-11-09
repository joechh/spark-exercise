package ScalaBook.ch11

/**
  * Created by joechh on 2017/5/24.
  */
object Example11_13_1 extends App {

  class List[+T](val head: T, val tail: List[T]) {
    def prepend[U >: T](newHead: U): List[U] = new List(newHead, this)
  }


  val list: List[Any] = new List[String]("not changed", null)

}
