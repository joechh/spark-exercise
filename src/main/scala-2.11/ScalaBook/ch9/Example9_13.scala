package ScalaBook.ch9

/**
  * Created by joechh on 2017/5/21.
  */
object Example9_13 extends App {

  class Dog(val name: String, val age: Int)

  object Dog {
    def unapply(dog: Dog): Option[(String, Int)] = {
      if (dog != null) Some(dog.name, dog.age)
      else None
    }
  }


  def patternMatching(x: AnyRef) = x match {
    case Dog(_, age) => println(s"Dog age = $age")
    case _ =>
  }

  val dog = new Dog("wawa", 20)
  patternMatching(dog)

}
