package ScalaBook.ch6

/**
  * Created by joechh on 2017/5/21.
  */
object Example6_15 extends App {

  class Person {
    //private var name: String = _
    private[this] var name: String = _

    def this(name: String) {
      this
      this.name = name
    }

    def print = println(name)
    this.name
  }

  object Person {
    //def printName = println(new Person("Joe").name)
  }

  //Person.printName

}
