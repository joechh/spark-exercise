var times = 3
times match {
  case 1 => "one"
  case 2 => "two"
  case 3 => "three"
  case _ => "others"
}

val number: String = times match {
  case 1 => "one"
  case 2 => "two"
  case 3 => "three"
  case _ => "100"
}


times match {
  case i if i == 1 => i
  case i if i == 2 => i * i
  case i if i == 3 => i * i * i
  case _ => "others"
}

def matchFunction(x: Int): String = x match {
  case 1 => "one"
  case 2 => "two"
  case _ => "others"
}
matchFunction(1)
matchFunction(2)
matchFunction(3)

def matchDiffType(item: Any): String = item match {
  case i: Int => "this is an integer value"
  case i: String => "this is a string value"
  case i: Double => "this is a double value"
}

matchDiffType(20)
matchDiffType("twenty")
matchDiffType(3.14)



