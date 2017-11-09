
//Apply,syntactic sugar
class Foo {}

object FooMaker {
  def apply() = new Foo
}

val newFoo = FooMaker()

//OR

class Bar {
  def apply() = 0
}

var bar = new Bar
bar()

//our instance(bar) looks like calling a method

//Object-hold single instances of a class (factories pattern)
object Timer {
  var count = 0

  def currentCount(): Long = {
    count += 1
    count
  }
}

Timer.currentCount()
Timer.currentCount()
Timer.currentCount()

//Companion Object
class Car(n: String) {
  val name = n
}

object Car {
  def apply(name: String) = new Car(name)
}

//Remove use 'new ' to create an instance
val car1 = Car("Toyota")
car1.name
val car2 = Car("BMW")
car2.name

//Functions are Objects,using apply we learned earlier, Function0-Function22 trait
//Thus ,Function could be seen as object
//For example, explicit reference
object addOne extends Function1[Int, Int] {
  override def apply(v: Int): Int = v + 1
}

addOne(2)

//short-hand Function2[Int,Int,Int] is extends (Int=>Int)
object adder extends ((Int, Int) => Int) {
  override def apply(v1: Int, v2: Int): Int = v1 + v2
}

addOne(2)

//Package see colorHolder & testPackageColorHolderApp

