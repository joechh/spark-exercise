package ScalaBook.ch11

/**
  * Created by joechh on 2017/5/24.
  */
object Example11_17 extends App{

  abstract class Food

  class Rice extends Food {
    override def toString = s"Rice()"
  }

  class Meat extends Food {
    override def toString = s"Meat()"
  }

  class Animal {
    type FoodType

    def eat(f: FoodType) = f
  }

  class Human extends Animal {
    type FoodType = Food

    override def eat(f: FoodType) = f
  }

  class Tiger extends Animal {
    type FoodType = Meat

    override def eat(f: FoodType) = f
  }

  val human = new Human
  val tiger = new Tiger
  println("human can eat:" + human.eat(new Rice))
  println("human can eat:" + human.eat(new Meat))
  println("Tiger can eat:" + tiger.eat(new Meat))

}
