package ScalaBook.ch11

/**
  * Created by joechh on 2017/5/23.
  */
object Example11_8 extends App {

  case class Student[T, S <: Comparable[S]](var name: T, var height: S)

  val a = Student("john", "joe")

  //!Error Int does not implement Comparable(But RichInt does)
  //val b = Student("john", 123)


}
