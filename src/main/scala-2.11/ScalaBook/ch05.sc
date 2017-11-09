import org.apache.calcite.linq4j.function.Function2

val sum = (x: Int, y: Int) => x + y
sum(1, 2)

val arrInt = Array(1, 2, 3)
val incrementalOne = (x: Int) => x + 1
arrInt.map(incrementalOne)
val increment2 = (x: Double) => x + 1

def higherOrderFunction(f: (Double) => Double) = f(100)
higherOrderFunction(increment2)

def higherOrderFunction2(x: Int) = (y: Double) => x * y

def multipler = higherOrderFunction2(10)
multipler(2)

val list = List("Spark" -> 1, "hive" -> 2, "hadoop" -> 2)
list.map(_._1)
val map = Map("Spark" -> 1, "hive" -> 2, "hadoop" -> 2)
map.map(_._1)


higherOrderFunction2(10)(10)

def curryingMultipler(factor: Int)(x: Double) = factor * x
curryingMultipler(1)(2)
val paf = curryingMultipler(1) _
paf(10)
val paf2 = curryingMultipler _
paf2(3)
paf2(3)(6)

def partSum = sum(_: Int, 3: Int)
partSum(1)

val sample = 1 to 10
val isEven: PartialFunction[Int, String] = {
  case x if x % 2 == 0 => x + " is even"
}
isEven(10)
//isEven(9)

sample.collect(isEven)

val isOdd: PartialFunction[Int, String] = {
  case x if x % 2 == 1 => x + " is odd"
}
isOdd(11)

val numbers = sample.map(isEven.orElse(isOdd))

def receive: PartialFunction[Any, Unit] = {
  case x: Int => println("int type")
  case x: String => println("String type")
  case _ => println("other type")
}
receive(10)
receive("10")
receive(3.14)

val valReceive: PartialFunction[Any, Unit] = {
  case x: Int => println("int type")
  case x: String => println("String type")
  case _ => println("other type")
}