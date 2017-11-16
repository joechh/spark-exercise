val map = Map(1 -> "one", 2 -> "two", 3 -> "three")
map(1)
map(3)
//map(5) //what happened?
map.getOrElse(5, "no value")
