package ScalaBook.ch9

import java.util.regex.Pattern

/**
  * Created by joechh on 2017/5/22.
  */
object Example9_19 extends App {

  sealed trait DeployMessage

  case class RegisterWork(id: String, host: String, port: Int) extends DeployMessage

  case class UnRegisterWork(id: String, host: String, port: Int) extends DeployMessage

  case class Heartbeat(workerId: String) extends DeployMessage

  def handleMessage(msg: DeployMessage) = msg match {
    case RegisterWork(id, host, port) => s"the worker $id is registering on $host:$port"
    case UnRegisterWork(id, host, port) => s"the worker $id is unregistering on $host:$port"
    case Heartbeat(id) => s"the worker $id is sending hearbeat"
  }

  val msgRegister=RegisterWork("123","localhost",8080)
  println(handleMessage(msgRegister))

}
