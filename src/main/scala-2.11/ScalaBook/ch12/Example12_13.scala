package ScalaBook.ch12

import ScalaBook.ch12.Example12_3.StringActor
import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging
import akka.routing.{ActorRefRoutee, RandomRoutingLogic, RoundRobinRoutingLogic, Router}

/**
  * Created by joechh on 2017/5/24.
  */
object Example12_13 extends App {

  class IntActor extends Actor {
    val log = Logging(context.system, this)

    override def receive: Receive = {
      case i: Int => log.info("received message:\n" + i)
      case _ =>
    }
  }

  class OtherActor extends Actor {
    var router = {
      val routees = Vector.fill(2) {
        val r = context.actorOf(Props[IntActor])
        context watch r
        ActorRefRoutee(r)
      }
      Router(RandomRoutingLogic(), routees)
    }

    override def receive: Receive = {
      case msg: Int => router.route(msg, sender())
    }
  }

  val system = ActorSystem("IntActorSystem")
  val otherActor = system.actorOf(Props[OtherActor], name = "OtherActor")
  for (i <- 1 to 10) {
    otherActor ! i
  }
  Thread.sleep(2000)
  system.terminate()

}
