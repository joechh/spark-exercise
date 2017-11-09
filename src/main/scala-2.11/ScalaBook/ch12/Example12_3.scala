package ScalaBook.ch12

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.event.Logging

/**
  * Created by joechh on 2017/5/24.
  */
object Example12_3 extends App {

  class StringActor extends Actor {
    val log = Logging(context.system, this)

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
      }
      case _ => log.info("received unknown message")
    }
  }

  val system = ActorSystem("StringSystem")
  val contextActor = system.actorOf(Props[ContextActor], name = "ContextActor")


  contextActor ! "Createing Actors with implicit val context"
  Thread.sleep(2000)
  system.terminate()


}
