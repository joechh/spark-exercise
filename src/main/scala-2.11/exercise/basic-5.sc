case class Computer(brand: String, capacity: String)

val hp = Computer("HP", "40g")
val mac = Computer("Apple", "128g")

hp.brand
hp.capacity
mac.brand
mac.capacity


def computerParser(computer: Computer) = computer match {
  case Computer("HP", "40g") => "its hp with 40g"
  case Computer("Apple", "128g") => "its mac with 128g"
  case Computer("Apple", _) => "its mac with XXXg"
  case Computer(_, "10g") => "its XXX PC with 10g"
}

computerParser(hp)

val numbers = Array(1, 2, 3)
numbers(0)
numbers(1)
numbers(0) = 100
numbers(0)
val numbersList = List(1, 2, 3)
val numbersSet = Set(1, 1, 2, 2, 3, 3, 3) //Set(1,2,3)

val hostPortPair = Tuple2("localhost", 80)
hostPortPair._1
hostPortPair._2
val portHostPair = hostPortPair.swap
portHostPair._1
portHostPair._2

hostPortPair match {
  case ("localhost", port) => println("local mode")
  case (host, port) => println("other mode")
}



