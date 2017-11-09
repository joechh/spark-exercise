package ScalaBook.ch12

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

/**
  * Created by joechh on 2017/5/24.
  */
object Example12_6 extends App {

  class StringActor extends Actor {
    val log = Logging(context.system, this)

    override def preStart(): Unit = {
      log.info("preStart method in StringActor")
    }

    override def postStop(): Unit = {
      log.info("postStop method in StringActor")
    }

    override def unhandled(message: Any): Unit = {
      log.info("unhandled method in StringActor")
      super.unhandled(message)
    }

    override def receive: Receive = {
      case s: String => log.info("received message:\n" + s)
      //case _ => log.info("received unknown message")
    }
  }

  val system = ActorSystem("StringSystem")
  val stringActor = system.actorOf(Props[StringActor], name = "StringActor")
  stringActor ! "creating actor with default constructor"

  stringActor ! 123

  Thread.sleep(2000)
  system.terminate()


}
