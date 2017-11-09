package ScalaBook.ch12

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.event.Logging

/**
  * Created by joechh on 2017/5/24.
  */
object Example12_1 extends App {

  class StringActor extends Actor {
    val log = Logging(context.system, this)

    override def receive: Receive = {
      case s: String => log.info("received message:" + s)
      case _ => log.info("received unknown message")
    }
  }

  val system = ActorSystem("StringSystem")
  val stringActor = system.actorOf(Props[StringActor])

  stringActor ! "Creating actors with default constructors"
  stringActor.tell("Creating actors with default constructors", ActorRef.noSender)
  system.terminate()
}
