package ScalaBook.ch10

/**
  * Created by joechh on 2017/5/22.
  */
object Example10_4 extends App {

  class TestA {
    override def toString = "this is TestA"

    def printA = println(this)
  }

  class TestB {
    override def toString = "this is TestB"

    def printB(x:TestC) = println(this)
  }

  class TestC {
    override def toString = "this is TestC"

    def printC = println(this)
  }

  implicit def AtoB(x: TestA) = {
    println("TestA -> TestB")
    new TestB
  }


  implicit def BtoC(x: TestB) = {
    println("TestB -> TestC")
    new TestC
  }


val a = new TestA
a.printB(new TestB)
}
