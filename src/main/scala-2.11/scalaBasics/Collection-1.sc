//Arrays, preserve order, can contain duplicate, and are mutable
val numbersArray = Array(1, 2, 3)
numbersArray(1)
numbersArray(1) = 100
numbersArray.foreach(println)

//Arrays, preserve order, can contain duplicate, and are Immutable
val numbersList = List(1, 2, 3)
numbersList(1)
//!numbersList(1)=100

//Set, do not preserve order and have no duplicates
val numbersSet = Set(1, 1, 2, 3)
numbersSet.foreach(println)

//Tuple, groups together simple logical collections of items
val hostPort = ("localhost", 80)
hostPort._1
hostPort._2

//Good fit for pattern match
hostPort match {
  case ("localhost", port) => println("local mode")
  case (host, port) => println("other mode")
}

//Tuple has special sauce for simply making Tuple:->
val hostPortRemote = "remote" -> 80
hostPortRemote._1
hostPortRemote._2

//Map, It can hold basic datatypes.
Map((1, 2))
Map(1 -> 2)
Map((1, 2), (3, 4))
val map = Map(1 -> 2, 3 -> 4)
map.get(1) //check source code
map.get(2)

//Option,is a container that may or may not hold something
//[LOGIC! not source code]
//trait Option[T] {
//  def isDefined: Boolean
//  def get: T
//  def getOrElse(t: T): T
//}

val value1 = map.get(1).get
//!val value2 = map.get(2).get //Exception
val value2 = map.get(2).getOrElse(0)

//Good work with patten match
val result = map.get(2) match {
  case Some(n) => n * 2
  case None => 0
}


