package ScalaBook.ch10

/**
  * Created by joechh on 2017/5/22.
  */
object Example10_3 extends App {

  trait Multiplicable[T] {
    def multiply(x: T): T
  }

  class MultiplicableInt extends Multiplicable[Int] {
    override def multiply(x: Int): Int = x * x
  }

  class  MultiplicableString extends Multiplicable[String] {
    override def multiply(x: String): String = x * 2
  }

  def multiply[T: Multiplicable](x: T)(implicit ev: Multiplicable[T]) = {
    ev.multiply(x)
  }

  implicit val mInt= new MultiplicableInt
  implicit val mString= new MultiplicableString

  println(multiply(5))
  println(multiply("joe"))

}
