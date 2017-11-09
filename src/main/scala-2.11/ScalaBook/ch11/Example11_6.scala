package ScalaBook.ch11

/**
  * Created by joechh on 2017/5/23.
  */
object Example11_6 extends App {

  case class Person(var name: String, var age: Int)
    extends Comparable[Person] {
    override def compareTo(o: Person): Int = {
      if (this.age > o.age) 1
      else if (this.age == o.age) 0
      else -1
    }
  }

  class TypeVariableBound {
    def compare[T <: Comparable[T]](first: T, second: T) = {
      if (first.compareTo(second) > 0)
        first
      else second
    }
  }

  val tvb = new TypeVariableBound
  println(tvb.compare("A", "B"))
  println(tvb.compare(Person("joe",33),Person("doris",30)))



}
