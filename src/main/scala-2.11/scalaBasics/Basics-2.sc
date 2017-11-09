//Classes
class Calculator {
  val brand: String = "HP"

  def add(m: Int, n: Int): Int = m + n
}

val calc = new Calculator

calc.add(1, 2)
calc.brand

//Constructor
class CalculatorWithConstruct(brand: String) {

  val color: String = if (brand == "TI") "blue" else "white"

  def add(m: Int, n: Int): Int = m + n
}

val calcNew = new CalculatorWithConstruct("haha")

calcNew.add(1, 2)
calcNew.color

//Inheritance
class ScientificCalculator(brand: String) extends CalculatorWithConstruct(brand) {
  def log(m: Double, base: Double) = math.log(m) / math.log(base)
}

//Abstract Classes
abstract class Shape {
  def getArea(): Double
}

//! val s = new Shape

class Circle(r: Int) extends Shape {
  override def getArea(): Double = {
    r * r * Math.PI
  }
}

//Traits: collections of fields and behaviors and be used to extend or minin to classes
trait Car {
  val brand: String
}

trait FlyAble {
  val equipment: String
}

class BMW extends Car with FlyAble {
  override val brand: String = "BMW-X7"
  override val equipment: String = "wing"
}

//If you want to define an interface-like type,
// you might find it difficult to choose between a trait or an abstract class.
// Either one lets you define a type with some behavior,
// asking extenders to define some other behavior.
// Some rules of thumb:
//    * Favor using traits.
//      It’s handy that a class can extend several traits; a class can extend only one class.
//    * use an abstract class if you need a constructor parameter:
//!trait t(i: Int) {} cannot work, but abstract can

//Generic with trait

trait Cache[K, V] {
  def get(key: K): V
  def put(key: K, value: V)
  def delete(key: K)
}

