import scala.util.matching.Regex

val (first, second) = (1, 2)
val rgex ="""(\d\d\d\d)-(\d\d)-(\d\d)""".r
for (date <- rgex.findAllIn("2015-12-312016-01-01"))
  println(date)

for (date <- rgex.findAllMatchIn("2015-12-312016-01-01")) {
  println(date.group(0) + ":" + date.group(1) + ":" + date.group(2) + ":" + date.group(3))
  println(date.groupCount)

}

rgex.findFirstIn("2015-12-312016-01-01") match {
  case Some(date) => "the date is " + date
  case None =>
}


val dateP2=new Regex("""(\d\d\d\d)-(\d\d)-(\d\d)""","year","month","day")
val res=dateP2.findFirstMatchIn("2015-12-312016-01-01") match{
  case Some(m) =>m.group("year")
}

for((lang,framework)<-Map("java"->"hadoop","closure"->"Storm","Scala"->"Spark"))
  println(s"$framework is developed by $lang language")

for((lang,framework:String)<-Map("java"->"hadoop".length,"closure"->"Storm","Scala"->"Spark".length))
  println(s"$framework is developed by $lang language")


