package ScalaBook.ch12


import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging
import akka.util.Timeout

import scala.concurrent.Future
import akka.pattern.ask

import scala.concurrent.duration._
import akka.pattern.pipe

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by joechh on 2017/5/24.
  */
object Example12_5 extends App {

  case class BasicInfo(id: Int, name: String, age: Int)

  case class InterestInfo(id: Int, interest: String)

  case class Person(basicInfo: BasicInfo, interestInfo: InterestInfo)

  class BasicInfoActor extends Actor {
    val log = Logging(context.system, this)

    override def receive: Receive = {
      case id: Int => log.info("id=" + id); sender ! new BasicInfo(id, "Joe", 19)
      case _ => log.info("receive unknown message")
    }
  }

  class InterestInfoActor extends Actor {
    val log = Logging(context.system, this)

    override def receive: Receive = {
      case id: Int => log.info("id=" + id); sender ! new InterestInfo(id, "Basketball")
      case _ => log.info("receive unknown message")
    }
  }

  class PersonActor extends Actor {
    val log = Logging(context.system, this)

    override def receive: Receive = {
      case person: Person => log.info("person=" + person)
      case _ => log.info("receive unknown message")
    }
  }

  class CombineActor extends Actor {
    implicit val timeout = Timeout(5 seconds)
    val basicInfoActor = context.actorOf(Props[BasicInfoActor], name = "BasicInfoActor")
    val interestInfoActor = context.actorOf(Props[InterestInfoActor], name = "InterestInfoActor")
    val personActor = context.actorOf(Props[PersonActor], name = "PersonActor")

    def receive: Receive = {
      case id: Int => {
        val combineResult: Future[Person] =
          for {
            basicInfo <- (basicInfoActor ? id).mapTo[BasicInfo]
            interestInfo <- (interestInfoActor ask id).mapTo[InterestInfo]
          } yield Person(basicInfo, interestInfo)
        pipe(combineResult).to(personActor)
      }

    }
  }

  val system = ActorSystem("Send-And-Receive-Future")
  val combineActor = system.actorOf(Props[CombineActor], name = "CombineActor")
  combineActor ! 12345
  Thread.sleep(5000)
  system.terminate()


}
