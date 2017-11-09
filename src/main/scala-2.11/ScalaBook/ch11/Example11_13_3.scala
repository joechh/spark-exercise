package ScalaBook.ch11

/**
  * Created by joechh on 2017/5/24.
  */
object Example11_13_3 extends App {

  class Person[-A] {

    def test(x: A): Unit = {
      println(x)
    }

    def test2[U <: A](x: U): U = null.asInstanceOf[U]
  }

  val pAny = new Person[Any]

  val pStr: Person[String] = pAny
  pStr.test("Person[String] >: Person[Any]")

}
