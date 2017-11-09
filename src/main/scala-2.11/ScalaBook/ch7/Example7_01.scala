package ScalaBook.ch7

/**
  * Created by joechh on 2017/5/21.
  */
object Example7_01 extends App {

  case class Person(var id: Int, var name: String, var age: Int)

  trait PersonDAO {
    println("now in the trait PersonDAO")
    var recordNum: Int = _
    var recordNum2: Int

    def add(p: Person)

    def update(p: Person): Unit = {
      println("invoking update method in PersonDAO update")
    }

    def delete(id: Int)

    def findById(id: Int): Person
  }

  trait PersonDAO2 {
    println("now in the trait PersonDAO2")

  }

  class PersonDAOImpl extends PersonDAO2 with PersonDAO{
    override def add(p: Person): Unit = println("invoking add method")

    //override def update(p: Person): Unit = println("invoking update method")

    override def delete(id: Int): Unit = println("invoking delete method")

    override def findById(id: Int): Person = {
      println("invoking find method")
      Person(100, "joe", 20)
    }

    override var recordNum2: Int = 20
  }

  val p: PersonDAO = new PersonDAOImpl
  p.add(Person(100, "joe", 20))
  p.update(Person(100, "joe", 20))

}
