import scala.collection.immutable.Queue
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

val mutableSet = mutable.Set(1, 2, 3)
val immutableSet = Set(1, 2, 3)

val numberArray = new Array[Int](10)
numberArray(0) = 20
numberArray

val strArrayVar = ArrayBuffer[String]()
strArrayVar += "Hello"
strArrayVar += ("world", "programmers")
strArrayVar
strArrayVar ++= Array("a", "b", "c")
strArrayVar ++= List("A", "B", "C")
strArrayVar.trimEnd(5)
strArrayVar

val intArrayVar = ArrayBuffer(1, 2, 3)
intArrayVar.insert(0, 10)
intArrayVar
intArrayVar.insert(0, 6, 7, 8)
intArrayVar
intArrayVar.remove(0, 4)
intArrayVar

var a = for (i <- intArrayVar) yield i + 2

val intArr = Array(1, 2, 3, 4, 5)
intArr.sum
intArr.max
intArr.min
intArr.mkString(",")
intArr.mkString("$", ",", ">")

val ListStr = List("spark", "hive", "flink")
val ListInt = List(1, 2, 3)
val num1 = 1 :: (2 :: (3 :: (4 :: Nil)))
val num2 = 1 :: 2 :: 3 :: 4 :: Nil
num1.isEmpty
num1.head
num1.tail
num1.tail.head
num1.tail.tail.head

num1 ::: num2
num1.init
num1.last

num1.reverse
num1.reverse.init
num1.drop(3)
num1.take(2)
num1.splitAt(3)
num1.zip(num2)
num1.toString.charAt(0)
num1.mkString(",")
num1.mkString
num1.toArray
List.range(2, 6)
List.range(6, 2, -1)
num1.zip(num2).unzip
List(List(1, 2, 3), List(4, 5)).flatten
List(1, 2, 3) ::: List(4, 5, 6) ::: List(7)
List.concat(List(1, 2, 3), List(4, 5, 6))


val numSet = scala.collection.mutable.Set(1, 2, 3)
numSet + 10
numSet + 1
val map = Map(1 -> "joe", 2 -> "doris")
for (i <- map)
  println(i)
map.foreach(x => println(x._1 + ":" + x._2))
val xMap = new mutable.HashMap[String, Int]()
xMap.put("joe", 1)
xMap
xMap.put("doris", 2)
xMap
xMap.put("joe", 3)
xMap
xMap.get("joe")
xMap.get("joeee")

val queue = Queue(1, 2, 3)
queue.dequeue._2
queue.enqueue(10)

val stack = mutable.Stack(1, 2, 3)
stack.pop()
stack.push(100)
stack.top
stack
stack.pop()
stack
stack.push(2)

def sum(x: Int, y: Int): Int = return x + y