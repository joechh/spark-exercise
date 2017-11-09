package ScalaBook.ch6

/**
  * Created by joechh on 2017/5/20.
  */
object Example6_11 extends App {

  class Person(var name: String, var age: Int) {


    def walk() = println("walk() method in Person")

    def talkTo(p: Person) = println("talkTo() method in Person")
  }

  class Student(name: String, age: Int, var studentNo: Int = 0) extends Person(name, age) {
    override def talkTo(p: Person): Unit = println(this.name + " is talking to " + p.name)
  }

  class Teacher(name: String, age: Int) extends Person(name, age) {

    override def walk() = println("walk() method in Teacher")

    override def talkTo(p: Person) = println(this.name + " is talking to " + p.name)


  }

  val p1: Person = new Teacher("albert", 38)
  val p2: Person = new Student("john", 30)

  p1.walk()
  p1.talkTo(p2)
  p2.walk()
  p2.talkTo(p1)


}
