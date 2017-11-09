//Pattern Matching, one of the most useful parts of Scala
var times = 1
times match {
  case 1 => "one"
  case 2 => "two"
  case _ => "others"
}

//Matching with guards, capture the value in teh variable i
times = 2
times match {
  case i if i == 1 => i * i
  case i if i == 2 => i * i * i
  case _ => "others"
}

//Matching on type
def bigger(o: Any): Any = {
  o match {
    case i: Int if i < 0 => i - 1
    case i: Int => i + 1
    case d: Double if d < 0.0 => d - 0.1
    case d: Double => d + 0.1
    case text: String => text + "s"
  }
}
bigger(20)
bigger(20.3)
bigger("20")

//Matching on class members
def calcType(calc: Calculator) = calc match {
  case _ if calc.brand == "HP" && calc.model == "20B" => "financial"
  case _ if calc.brand == "HP" && calc.model == "40B" => "scientific"
  case _ if calc.brand == "HP" && calc.model == "60B" => "business"
  case _ => "unknown"
}

//Case Classes: conveniently store and match on the contents of a class
case class Calculator(brand: String, model: String)

val hp20b = Calculator("HP", "20B")

calcType(hp20b)
//automatically have equality and nice toString methods
//based on the constructor arguments.
println(hp20b)
println(hp20b.brand)
println(hp20b.model)


def calcTypeWithCaseClass(calc: Calculator) = calc match {
  case Calculator("HP", "20B") => "financial"
  case Calculator("HP", "40B") => "scientific"
  case Calculator("HP", "60B") => "business"
  case Calculator(_, _) => "unknown type"
}

calcTypeWithCaseClass(hp20b)