package ScalaBook.ch12

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

/**
  * Created by joechh on 2017/5/24.
  */
object Example12_4 extends App {

  case class Start(var msg: String)

  case class Run(var msg: String)

  case class Stop(var msg: String)

  class ExampleActor extends Actor {
    val other = context.actorOf(Props[OtherActor], "OtherActor")
    val log = Logging(context.system, this)

    override def receive: Receive = {
      case Start(msg) => other ! msg
      case Run(msg) => other.tell(msg, sender)
      case _ => log.info("received unknown message")
    }
  }

  class OtherActor extends Actor {
    val log = Logging(context.system, this)

    override def receive: Receive = {
      case s: String => log.info("received message:\n" + s)
      case _ => log.info("received unknown message")
    }
  }


  val system = ActorSystem("StringSystem")
  val exampleActor = system.actorOf(Props[ExampleActor], name = "ExampleActor")
  exampleActor ! Run("Running")
  exampleActor ! Start("Starting")

  Thread.sleep(2000)
  system.terminate()


}
