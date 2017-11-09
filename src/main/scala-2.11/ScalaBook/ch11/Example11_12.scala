package ScalaBook.ch11

/**
  * Created by joechh on 2017/5/23.
  */
object Example11_12 extends App {

  class A[T]

  class B[T]

  implicit val a = new A[String]
  implicit val b = new B[String]

  def test[T: A : B](x: T) = println(x)

  test("testing")

  implicit def t2A[T](x: T) = new A[T]

  implicit def t2B[T](x: T) = new B[T]

  def test2[T <% A[T] <% B[T]](x: T) = println(x)
  test("testing2")


}
