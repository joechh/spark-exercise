val name = "Joe"
val gender: String = "male"

var varName = "Joe"
varName = "Doris"

def addOne(m: Int): Int = m + 1
def addTwo(m: Int) = m + 2
val three = addOne(2)
val four = addTwo(2)

def timesTwo(i: Int): Int = {
  println("hello world")
  i * 2 //最後一行會被當作回傳值
}

timesTwo(2)

def sqrt(x: Double) = Math.sqrt(x) //normal function
def higherOrderFunction(y: (Double) => Double, x: Int) = {
  y(x)
}

higherOrderFunction(sqrt, 10)

higherOrderFunction((x: Double) => x + 1, 10)
higherOrderFunction(y => y * 3.14, 10)

def higherOrderFunctionWithReturn(factor: Int): (Double => Double) = {
  println("return new function:")
  (x: Double) => factor * x //回傳一個函式

}

def multiply = higherOrderFunctionWithReturn(100)

multiply(5)
multiply(2)

class Calculator {
  val brand: String = "HP"

  def add(m: Int, n: Int): Int = m + n
}

val calc = new Calculator
calc.add(1, 2)
calc.brand

class CalculatorWithConstruct(b: String) {
  val brand: String = b

  def add(m: Int, n: Int): Int = m + n

}

val anotherCalc = new CalculatorWithConstruct("Apple")
anotherCalc.brand

class ScientificCalculator(abc: String) extends CalculatorWithConstruct(abc) {
  def log(m: Double, base: Double) = math.log(m) / math.log(base)
}
val sciCalc = new ScientificCalculator("IBM")
sciCalc.log(100,10)









