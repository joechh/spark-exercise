package ScalaBook.ch9

/**
  * Created by joechh on 2017/5/21.
  */
object Example9_6 extends App {

  case class Dog(val name: String, val age: Int)


  def patternMatching(x: Any) = x match {
    case Dog(name, age) => println(s"Dog name = $name, age = $age")
    case _ =>
  }
  val dog = Dog("wawa", 20)
  patternMatching(dog)
}
