trait Closable {
  def close(): Unit
}

class File(var name: String) extends Closable {
  override def close(): Unit = println(s"File $name, has been closed")
}

new File("testfile").close()

class File2(var name: String) extends java.io.File(name) with Closable with Cloneable {
  override def close(): Unit = println(s"File $name, has been closed")
}

trait Processable extends Closable with Cloneable

abstract class File3(var name: String) extends java.io.File(name) with Closable with Cloneable {

}

abstract class Logger(val msg: String)

trait Logger2


trait A {
  val A = "Trait A"

  def print(msg: String) = println(msg + ":" + A)
}

trait B1 extends A {
  val B1 = "Trait B1"

  override def print(msg: String): Unit = println(msg + ":" + B1)
}

trait B2 extends A {
  val B2 = "Trait B2"

  override def print(msg: String): Unit = println(msg + ":" + B2)
}

class C extends B1 with B2

new C().print("print method in")

trait BB

trait AA {
  this: BB =>
}

class CC extends AA with BB

trait Prompter1 {
  val prompt = "> "
  val greeting = "Hello world"

  def printGreeting(): Unit = {
    println(prompt + greeting)
  }
}

val prompter1 = new Object with Prompter1
prompter1.printGreeting()
