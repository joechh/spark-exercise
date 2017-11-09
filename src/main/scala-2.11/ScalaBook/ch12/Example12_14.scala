package ScalaBook.ch12

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, ActorLogging, ActorSystem, OneForOneStrategy, Props}
import akka.event.Logging
import akka.routing.{ActorRefRoutee, RandomRoutingLogic, Router}
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.Await
import scala.concurrent.duration._


/**
  * Created by joechh on 2017/5/24.
  */
object Example12_14 extends App {

  //  override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
  //    case _: ArithmeticException => Resume
  //    case _: NullPointerException => Restart
  //    case _: IllegalArgumentException => Stop
  //    case _: Exception => Escalate
  //  }

  case class NormalMessage()

  class ChildActor extends Actor with ActorLogging {
    var state: Int = 0


    override def preStart(): Unit = {
      log.info("start childActor, its hashcode is " + this.hashCode())
    }

    override def postStop(): Unit = {
      log.info("stop childActor, its hashcode is " + this.hashCode())
    }

    override def receive: Receive = {
      case value: Int => {
        if (value <= 0)
          throw new ArithmeticException("value <=0")
        else
          state = value
      }
      case result: NormalMessage => sender ! state
      case ex: NullPointerException => throw new NullPointerException("null pointer")
      case _ => throw new IllegalArgumentException("illegal argument")
    }
  }

  class SupervisorActor extends Actor with ActorLogging {
    val childActor = context.actorOf(Props[ChildActor], name = "ChildActor")
    override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
      case _: ArithmeticException => Resume
      case _: NullPointerException => Restart
      case _: IllegalArgumentException => Stop
      case _: Exception => Escalate
    }

    override def receive: Receive = {
      case msg: NormalMessage => childActor.tell(msg, sender)
      case msg: Object => childActor ! msg
    }
  }

  val system = ActorSystem("FaultToleranceSystem")
  val log = system.log
  val supervisorActor = system.actorOf(Props[SupervisorActor], name = "SupervisorActor")


  implicit val timeout = Timeout(5 second)

  //positive
  supervisorActor ! 5
  var future = (supervisorActor ? new NormalMessage).mapTo[Int]
  var resultMsg = Await.result(future, timeout.duration)
  log.info("res:" + resultMsg)

  //negative
  supervisorActor ! -5
  future = (supervisorActor ? new NormalMessage).mapTo[Int]
  resultMsg = Await.result(future, timeout.duration)
  log.info("res:" + resultMsg)

  //NullPointException
  supervisorActor ! new NullPointerException
  future = (supervisorActor ? new NormalMessage).mapTo[Int]
  resultMsg = Await.result(future, timeout.duration)
  log.info("res:" + resultMsg)

  //Illegal Argument
  supervisorActor ? "Illegal String"


  Thread.sleep(2000)
  system.terminate()


}
