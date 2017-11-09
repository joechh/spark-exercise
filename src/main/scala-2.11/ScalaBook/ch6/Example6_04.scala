package ScalaBook.ch6

/**
  * Created by joechh on 2017/5/20.
  */
object Example6_04 {

  class Person(val name: String = "default User", val age: Int = -1) {
    println("constructing Person...")

    override def toString: String = {
      name + ":" + age
    }

  }

  def main(args: Array[String]): Unit = {
    val p = new Person("Joe", 33)
    println(p)
    val defaultUser = new Person()
    println(defaultUser)
  }


}
