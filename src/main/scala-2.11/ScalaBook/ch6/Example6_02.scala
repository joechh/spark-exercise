package ScalaBook.ch6

/**
  * Created by joechh on 2017/5/20.
  */
object Example6_02 extends App {

  class Student {
    private var name: String = null

    def getStudentNo = {
      Student.uniqueStudentNo()
      Student.studentNo
    }
  }

  object Student {
    private var studentNo: Int = 0;

    def uniqueStudentNo() = {
      studentNo += 1
      studentNo
    }

    def printStudentName = println(new Student().name)
  }

  val student =new Student()
  println(student.getStudentNo)
  println(Student.printStudentName)


}
