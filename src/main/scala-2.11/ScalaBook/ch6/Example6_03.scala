package ScalaBook.ch6

/**
  * Created by joechh on 2017/5/20.
  */
object Example6_03 extends App {

  class Student {
    var name: String = null
  }

  object Student {
    def apply(): Student = new Student()

  }

  val s = Student()
  s.name = "joe"
  println(s.name)

}
