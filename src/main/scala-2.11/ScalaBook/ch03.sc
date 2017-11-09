val x = 9
val a = if (x < 8) 1 else 0

for (i <- 1 to 5)
  println(i)

import scala.util.control.Breaks._

val r = (1 to(5, 2))
for (i <- r) println(i)
val s = 1 until 5

val t = for (i <- 1 to 5) yield i + 2

