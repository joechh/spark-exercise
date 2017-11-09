import scala.beans.BeanProperty

class Person {
  @BeanProperty var name: String = null
}

val p = new Person()
p.name = "abc"
print(p.name)
p.setName("haha")
println(p.getName)


object Student{
  private var studentNo: Int = 0;
  def uniqueStudentNo()={
    studentNo+=1
    studentNo
  }

  def main(args: Array[String]): Unit = {
    print("ohya")
  }
}

Student.uniqueStudentNo()
Student.uniqueStudentNo()
