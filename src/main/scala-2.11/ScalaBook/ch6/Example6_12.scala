package ScalaBook.ch6

/**
  * Created by joechh on 2017/5/21.
  */
object Example6_12 extends App {

  abstract class Person {
    var age: Int = 0
    var name: String

    def walk()

    override def toString: String = name
  }

  class Student extends Person{
    override var name: String = _

    override def walk(): Unit = println("walk() method in Student")
  }

  val p = new Student
  p.walk()
}
