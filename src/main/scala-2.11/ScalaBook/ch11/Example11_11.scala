package ScalaBook.ch11

/**
  * Created by joechh on 2017/5/23.
  */
object Example11_11 extends App {

  class PersonOrdering extends Ordering[Person] {
    override def compare(x: Person, y: Person): Int = {
      if (x.name > y.name) 1 else -1
    }
  }

  case class Person(val name: String) {
    println("now is constructing object:" + name)
  }

  implicit val p1 = new PersonOrdering

  class Pair[T: Ordering](val first: T, val second: T) {

    import Ordered.orderingToOrdered;

    def smaller:T = if (first < second) first else second

  }

  val p = new Pair[Person](Person("123"), Person("456"))
  println(p.smaller)


}
