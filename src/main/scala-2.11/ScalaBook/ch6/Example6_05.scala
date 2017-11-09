package ScalaBook.ch6

/**
  * Created by joechh on 2017/5/20.
  */
object Example6_05 extends App {

  class Person {
    private var name: String = _
    private var age: Int = 18
    private var sex: Int = _

    def this(name: String) {
      this
      this.name = name
    }

    def this(name: String, age: Int) {
      this(name)
      this.age = age
    }

    def this(name: String, age: Int, sex: Int) {
      this(name, age)
      this.sex = sex
    }


    override def toString = s"Person(name=$name, age=$age, sex=$sex)"
  }
  println(new Person("joe",33,1))
  println(new Person("joe",33))
  println(new Person("joe"))

}
