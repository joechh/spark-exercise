package ScalaBook.ch10

/**
  * Created by joechh on 2017/5/22.
  */
object Example10_1 extends App {

  trait Multiplicable[T] {
    def multiply(x: T): T
  }

  implicit object MultiplicableInt extends Multiplicable[Int] {
    override def multiply(x: Int): Int = x * x
  }

  implicit object MultiplicableString extends Multiplicable[String] {
    override def multiply(x: String): String = x * 2
  }

  def multiply[T: Multiplicable](x: T): T = {
    val ev = implicitly[Multiplicable[T]]
    ev.multiply(x)
  }

  println(multiply(5))
  println(multiply("joe"))

}
