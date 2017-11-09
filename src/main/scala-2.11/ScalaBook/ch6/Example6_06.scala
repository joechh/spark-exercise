package ScalaBook.ch6

/**
  * Created by joechh on 2017/5/20.
  */
object Example6_06 extends App {

  class Person {
    private var name: String = _
    private var age: Int = 38
    private var sex: Int = _

    def this(name: String = "", age: Int = 18, sex: Int = 1) {
      this
      this.name = name
      this.age = age
      this.sex = sex
    }

    override def toString = s"Person(name=$name, age=$age, sex=$sex)"

  }


  println(new Person("joe"))
  println(new Person())


}
