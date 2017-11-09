package ScalaBook.ch11

/**
  * Created by joechh on 2017/5/24.
  */
object Example11_16 extends App {

  class Outter {
    val x: Int = 0

    def test(i: Outter#Inner) = i

    class Inner

  }

  val o1 = new Outter
  val inner1 = new o1.Inner
  o1.test(inner1)
  //!Error
  o1.test(inner2)

  val o2 = new Outter
  val inner2 = new o2.Inner


}
