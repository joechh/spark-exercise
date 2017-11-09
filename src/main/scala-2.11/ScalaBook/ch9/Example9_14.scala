package ScalaBook.ch9

import java.util.regex.Pattern

/**
  * Created by joechh on 2017/5/22.
  */
object Example9_14 extends App {
  val line = "Hadoop has been the most popular big data" + "processing tool since 2005-11-21"

  val regex = "(\\d\\d\\d\\d)-(\\d\\d)-(\\d\\d)"

  val pattenr =Pattern.compile(regex)

  val m=pattenr.matcher(line)

  if(m.find()){
    println(m.group(0))
    println(m.group(1))
    println(m.group(2))
    println(m.group(3))
  }
  else println("nothing")

}
