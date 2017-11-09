package ScalaBook.ch12

import akka.actor.TypedActor.{PostStop, PreStart}
import akka.actor.{ActorSystem, TypedActor, TypedProps}
import akka.event.Logging

import scala.concurrent.duration._
import scala.concurrent.{Await, Future, Promise}

/**
  * Created by joechh on 2017/5/24.
  */
object Example12_10 extends App {

  trait Squarer {
    def squareDontCare(i: Int): Unit

    def square(i: Int): Future[Int]

    def squareNowPlease(i: Int): Option[Int]

    def squareNow(i: Int): Int
  }

  class SquareImpl(var name: String) extends Squarer with PostStop with PreStart{

    def this() = this("SquarerImpl")

    override def squareDontCare(i: Int): Unit = i * i

    override def square(i: Int): Future[Int] = Promise.successful(i * i).future

    override def squareNowPlease(i: Int): Option[Int] = Some(i * i)

    override def squareNow(i: Int): Int = i * i

    override def postStop(): Unit = {
      log.info("TypedActor Stopped")
    }

    override def preStart(): Unit = {
      log.info("TypedActor Started")
    }
  }

  val system = ActorSystem("TypedActorSystem")
  val log = Logging(system, this.getClass)

  val mySquarer: Squarer = TypedActor(system).typedActorOf(TypedProps[SquareImpl], "mySquarer")

  val otherSquarer: Squarer = TypedActor(system).typedActorOf(TypedProps(classOf[Squarer], new SquareImpl("SquarerImpl")), "otherSquarer")

  mySquarer.squareDontCare(10)

  val oSquare = mySquarer.squareNowPlease(10)

  log.info("oSquare=" + oSquare)

  val iSquare=mySquarer.squareNow(10)
  log.info("iSquare=" + iSquare)

  val fSqure = mySquarer.square(10)
  val res = Await.result(fSqure,5 second)
  log.info("fSquare=" + fSqure)

  TypedActor(system).poisonPill(otherSquarer)
  TypedActor(system).stop(mySquarer)

  Thread.sleep(5000)
  //system.terminate()


}
