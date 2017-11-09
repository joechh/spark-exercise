package ScalaBook.ch11

/**
  * Created by joechh on 2017/5/24.
  */
object Example11_14 extends App {

  class Pet {
    private var name: String = null
    private var weight: Float = 0.0f

    def setName(name: String) = {
      this.name = name
      this
    }

    def setWeight(weight: Float) = {
      this.weight = weight
      this
    }


    override def toString = s"Pet(name=$name, weight=$weight)"
  }

  class Dog extends Pet {
    private var age: Int = 0

    def setAge(age: Int) = {
      this.age = age
      this
    }

    override def toString = super.toString + s"Dog(age=$age)"
  }

  val d:Pet = new Dog().setAge(2).setName("joe").setWeight(20.0f)


  //!Error ,new Dog().setName("joe").setWeight(20.0f) would return Pet, which does not know setAge function
  // println(new Dog().setName("joe").setWeight(20.0f).setAge(2))


}
