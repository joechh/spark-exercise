package ScalaBook.ch6

/**
  * Created by joechh on 2017/5/20.
  */
object Example6_01 {

  object Student {
    private var studentNo: Int = 0;

    def uniqueStudentNo() = {
      studentNo += 1
      studentNo
    }
  }

  def main(args: Array[String]) {
    println(Student.uniqueStudentNo())
    println(Student.uniqueStudentNo())
  }


}


