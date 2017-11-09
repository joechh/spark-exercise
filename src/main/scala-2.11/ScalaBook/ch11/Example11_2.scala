package ScalaBook.ch11

/**
  * Created by joechh on 2017/5/23.
  */
object Example11_2 extends App {

  class Person[T](var name: T)

  class Student[T, S](name: T, var age: S) extends Person(name)

  println(new Student[String, Int]("Joe", 32).name)


}
