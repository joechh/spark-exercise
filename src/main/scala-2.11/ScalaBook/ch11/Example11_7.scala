package ScalaBook.ch11

/**
  * Created by joechh on 2017/5/23.
  */
object Example11_7 extends App {

  case class Student[S, T <: AnyVal](var name: S, var height: T)

  //!Error, String is not :<AnyVal
  //val S1 = Student("john", "joe")
  val S2 = Student("john", 123)
  val S3 = Student("john", 123.4)


}
