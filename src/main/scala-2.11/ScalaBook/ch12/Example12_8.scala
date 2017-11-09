package ScalaBook.ch12

import akka.actor.{Actor, ActorSystem, PoisonPill, Props}
import akka.event.Logging

/**
  * Created by joechh on 2017/5/24.
  */
object Example12_8 extends App {

  class StringActor extends Actor {
    val log = Logging(context.system, this)

    override def postStop(): Unit = {
      log.info("postStop method in StringActor")
    }

    override def receive: Receive = {
      case s: String => log.info("received message:" + s)
      case _ => log.info("received unknown message")
    }

  }

  class ContextActor extends Actor {
    val log = Logging(context.system, this)
    var stringActor = context.actorOf(Props[StringActor], name = "StringActor")


    override def receive: Receive = {
      case s: String => {
        log.info("received message:" + s)
        stringActor ! s
        context.stop(stringActor)
      }
      case _ => log.info("received unknown message")
    }

    override def postStop(): Unit = {
      log.info("postStop method in ContextAcotr")
    }
  }

  val system = ActorSystem("StringSystem")
  val contextActor = system.actorOf(Props[ContextActor], name = "ContextActor")
  contextActor ! "createing actors with implic val context"
  contextActor ! PoisonPill

  Thread.sleep(2000)
  system.terminate()

}
