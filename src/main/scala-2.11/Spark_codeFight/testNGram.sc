val words = List("spark", "sql", "is", "a", "spark", "module")
println(words(0)+words(0))

//words.sliding(2).foreach( p => println(p.mkString))
words.sliding(2).toList.foreach( println)


def ngram(words: List[String], n: Int): Set[(String, Int)] = {
  for (i <- 0 to words.size - n) {
    var word = ""
    for (j <- 0 to n - 1)
      word = word + " "+word(i + j)
    print(word)
    println()
  }
  ???
}

ngram(words, 2)

