val date = """(\d\d\d\d)-(\d\d)-(\d\d)""".r
val date2 = """(\d\d\d\d-\d\d-\d\d)""".r

val dates = "Important dates in history: 2004-01-20, 1958-09-05, 2010-10-06, 2011-07-15"
val firstDate = date.findFirstIn(dates).getOrElse("No date found.")
val firstYear = for (m <- date.findFirstMatchIn(dates)) yield m.group(1)

val allYears = for (m <- date.findAllMatchIn(dates)) yield m.group(1)
allYears.foreach(println)

val test = date2.findAllMatchIn(dates)
test.foreach(x => println(x.group(1)))