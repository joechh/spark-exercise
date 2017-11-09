//Expreesions
1 + 1

//Values (immutable)
val two = 1 + 1
//!two=2

//Variable (mutable)
var name = "Joe"
name = "Doris"

//Functions (with def)
def addOne(m: Int): Int = m + 1 //specify the type signature
val three = addOne(2)

//Leave off parens on functions with no arguments
def four() = 3 + 1
four()
four

//Anonymous Function
(x: Int) => x + 1

//pass Anonymous Function or save them into val
val addOneFunction = (x: Int) => x + 1
addOneFunction(1)

//use {} if function is made up of many expression
def timesTwo(i: Int): Int = {
  println("hello world")
  i * 2 //The last expression would be returned
}

//The same rule could apply on anonymous Function
//see this syntax very often when passing
//an anonymous function as an argument
{ i: Int =>
  println("hello world")
  i * 2
}

//Partial application/placeholder/magical wildcard
def adder(m: Int, n: Int) = m + n
val add2 = adder(2, _: Int)
add2(3)

//Partial application+Curried functions, fill some, return the rest with unfilled parament
def multiply(m: Int)(n: Int): Int = m * n
multiply(2)(3)
val timesFour = multiply(4) _
timesFour(2)

//Can take any existing function of multiple
// arguments and curry it via curried method

val curriedAdd = (adder _).curried
curriedAdd(1)(2)

def adder2(m: Int, n: Int, o: Int) = m + n + o
val curriedAdd2 = (adder2 _).curried

//Variable length arguments
def capitalizeAll(args: String*) = {
  args.map { arg =>
    arg.capitalize
  }
}

capitalizeAll("joe", "doris", "abc")

def sqrt(x: Double) = Math.sqrt(x)
def higherOrderFunction(f: (Double) => Double) = f(100)
higherOrderFunction(sqrt)


def higherOrderFunction2(factor: Int): (Double => Double) = {
  println("return new function:")
  (x: Double) => factor * x
}

def multiply2 = higherOrderFunction2(100)
multiply2(2)










