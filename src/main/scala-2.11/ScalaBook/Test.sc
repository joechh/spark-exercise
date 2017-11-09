class Printable {
  def hello() = println("Hello World in printable")
}

trait Hello extends Printable {
  override def hello() = {
    println("Hello World in Hello")
    super.hello()
  }
}

trait CapitalHello extends Printable {
  override def hello() = {
    println("HELLO WORLD in CapitalHello")
    super.hello()
  }
}

val capital = new Printable with CapitalHello with Hello
capital.hello()