val words: String =
  """Spark SQL is a Spark module for structured data processing.
    |Unlike the basic Spark RDD API, the interfaces provided by Spark SQL provide
    |Spark with more information about the structure of both the data and the
    |computation being performed. Internally, Spark SQL uses this extra information
    |to perform extra optimizations. There are several ways to interact with Spark SQL
    |including SQL and the Dataset API. One use of Spark SQL is to execute SQL queries.
    |Spark SQL can also be used to read data from an existing Hive installation.
    |When running SQL from within another programming language the results will
    |be returned as a Dataset/DataFrame. You can also interact with the SQL interface
    |using the command-line or over JDBC/ODBC.""".stripMargin
val w = words.replaceAll("\n", " ").replaceAll("""[,.]""", "").split(" ")
val a = w.sliding(3)
while (a.hasNext) {
  println(a.next().toList)
}

val n = 4
val ngrams = (for (i <- 1 to n) yield w.sliding(i).map(p => p.toList)).flatMap(x => x)
ngrams foreach println

val numbers=List(1,2,3,4)
val aa=numbers.sliding(3)
while(aa.hasNext)
  println(aa.next())

numbers.sliding(3).toList


