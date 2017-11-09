import java.util

import com.sun.corba.se.impl.orb.ParserTable.TestAcceptor1

import scala.reflect.runtime.universe._

class Test

val a = new Test
typeOf[Test]
classOf[Test]
a.getClass

val listStr = new util.ArrayList[String]
val listInt = new util.ArrayList[Integer]

listStr.getClass
listInt.getClass

typeOf[List[Integer]]
typeOf[List[String]]

classOf[List[Integer]]
classOf[List[String]]

def print(x: Map[_, _]) = println(x)
def print2(x: Map[T, U] forSome {type T; type U}) = println(x)

type JavaHashMap = java.util.HashMap[String, String]
//type JavaHashMap = java.util.HashMap
val map = new JavaHashMap
map.put("Joe", "Doris")
map

//!Error import java.util.{HashMap[String,String]=>JavaHashMap}
class TestA

class TestB extends TestA with Cloneable

def test(x: TestA with Cloneable) = println("OK")

test(new TestB)

class TestC extends TestB

test(new TestC)

type CompoundType = TestA with Cloneable

def test2(x: CompoundType) = println("OK")

test2(new TestB)
test2(new TestC)


def test3(x: Double => Int) = x(6.0)
def double2Int(x: Double) = x.toInt

test3(double2Int)

val sum = new Function2[Int, Int, Int] {
  def apply(x: Int, y: Int): Int = x + y
}

sum(4, 6)

val sum2 = (x: Int, y: Int) => x + y

