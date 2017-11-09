package ScalaBook.ch11

/**
  * Created by joechh on 2017/5/23.
  */
object Example11_1 extends App {

  class Person[T](var name: T)

  class Student[T](name: T) extends Person(name)

  println(new Student[String]("Joe").name)


}
