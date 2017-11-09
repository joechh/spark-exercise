package ScalaBook.ch12

import akka.actor.TypedActor.{PostStop, PreStart}
import akka.actor.{Actor, ActorSystem, Props, TypedActor, TypedProps}
import akka.event.Logging
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._
import scala.concurrent.{Await, Future, Promise}

/**
  * Created by joechh on 2017/5/24.
  */
object Example12_11 extends App {

  class StringActor extends Actor {
    val log = Logging(context.system, this)

    override def receive: Receive = {
      case s: String => log.info("received message:\n" + s)
      case _ => log.info("received unknown message:\n")
    }

    override def postStop(): Unit = {
      log.info("postStop in StringActor")
    }

  }

  val system = ActorSystem.create("DispatcherSystem",ConfigFactory.load.getConfig("my-dispatcher"))

  val stringActor = system.actorOf(Props[StringActor].withDispatcher("mydefaultDispatcher"),name="StringActor")

  stringActor ! "Test"

  system.terminate()


}
