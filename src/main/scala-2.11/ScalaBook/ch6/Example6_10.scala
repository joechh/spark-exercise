
package ScalaBook.ch6

/**
  * Created by joechh on 2017/5/20.
  */
object Example6_10 extends App {

  class Person(var name: String, var age: Int) {
    override def toString = s"Person(name=$name, age=$age)"
  }

  class Student(name: String, age: Int, var studentNo: Int) extends Person(name, age) {
    override def toString = s"Student($name,$age,$studentNo)"
  }

  println(new Person("John", 18))
  println(new Student("doris", 29, 1000))
}


