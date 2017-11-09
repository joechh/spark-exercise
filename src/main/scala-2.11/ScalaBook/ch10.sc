

implicit def float2Int(x: Float): Int = {
  x.toInt
}
//implicit def f2i(x: Float): Int = {
//  x.toInt
//}
val intValue: Int = 2.55f

implicit class Dog(val name: String) {
  def bark = println(s"$name is barking1")
}

implicit class D(val name: String) {
  def bark2 = println(s"$name is barking2")
}

implicit class SpecialDog(val d: Dog) {
  def specialBark = println(s"${d.name} is barking loadly")
}

"Joe".bark
"Joe".bark2
//!String->Dog->Special Dog is not allowed(only-one rule) "Joe".specialBark

implicit val x: Double = 2.55
def sqrt(implicit x: Double) = Math.sqrt(x)
sqrt(10)

def product(implicit x: Double, y: Double) = x * y

product

product(1, 1)

//!Erro product(1.0)
//!Error def product2(implicit x: Double, implicit y: Double)=x*y
//!Error def product2( x: Double, implicit y: Double)=x*y =>should currying
def product2(x: Double)(implicit y: Double) = x * y
product2(10)

def sum(implicit x: Int, y: Int) = x + y
implicit val xx: Int = 5
val yyy = 20
sum
