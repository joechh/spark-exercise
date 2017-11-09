package ScalaBook.ch9

/**
  * Created by joechh on 2017/5/21.
  */
object Example9_10 extends App {

  class A

  class B extends A

  class C extends A


  def patternMatching(x: Any) = x match {
    case x: String => println("String type")
    //case x: A => println("type A")
    case x: B => println("type B")
    case x: A => println("type A")
    case _=>println("other type")
  }

  patternMatching(new A())
  patternMatching(new B())
  patternMatching(new C())
  patternMatching("this is a test")

}
