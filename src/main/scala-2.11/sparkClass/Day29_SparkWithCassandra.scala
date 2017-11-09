package sparkClass

import org.apache.spark.{SparkConf, SparkContext}


/**
  * Created by joechh on 2017/1/13.
  */
object Day29_SparkWithCassandra extends App {


  val conf = new SparkConf()
    .set("spark.cassandra.connection.host", "127.0.0.1")
    .setMaster("local[2]")
    .setAppName("cassandra")
    .set("cassandra.username","cassandra")
    .set("cassandra.password","cassandra")


  val sc = new SparkContext(conf)

  //Obtaining a Cassandra table as an RDD
  //To get a Spark RDD that represents a Cassandra table, call the `cassandraTable` method on the SparkContext object.
  //  val rdd = sc.cassandraTable("test", "kv")
  //  println(rdd.count)
  //  println(rdd.first)
  //  println(rdd.map(_.getInt("value")).sum)
  //  rdd.foreach(println)
  //
  //  //  //write demo
  //  val collection = sc.parallelize(Seq(("key3", 3), ("key4", 4)))
  //  collection.saveToCassandra("test", "kv", SomeColumns("key", "value"))

  //  val rdd = sc.cassandraTable("test", "words")
  //
  //  rdd.foreach(println)


  //
  //  // You can read columns in a Cassandra table using the get methods of the `CassandraRow` object.
  //  // The get methods access individual column values by column name or column index.
  //  // Type conversions are applied on the fly.
  //  // Use getOption variants when you expect to receive Cassandra null values.
  //  val firstRow = rdd.first
  //  println(firstRow.columnValues)
  //  println(firstRow.size)
  //  println("key: " + firstRow.getString("key"))
  //  println("value:" + firstRow.getInt("value"))
  //  println("value:" + firstRow.get[Int]("value"))
  //  println("value:" + firstRow.getIntOption("value").getOrElse(-1))
  //  println("value:" + firstRow.get[Option[Int]]("value").getOrElse(-1))
  //
  import com.datastax.spark.connector._

  val firstSetrow = sc.cassandraTable("test", "users").first
  //  println(firstSetrow)
  //  println(firstSetrow.getList[String]("emails"))
  //  println(firstSetrow.get[List[String]]("emails"))
  //  println(firstSetrow.get[String]("emails"))
  //
  //  val udfrow = sc.cassandraTable("testspace", "users_with_udfs").first
  //  val addressMap = udfrow.getMap[String, UDTValue]("addresses")
  //  val address = addressMap.get("home")
  //
  //  val address2 = address.get
  //  val city = address2.getString("city")
  //  val street = address2.getString("street")
  //  val zip = address2.getInt("zip_code")
  //
  //  println(address)
  //  println(zip + " " + city + ", " + street)

  val internalJoin = sc.cassandraTable("test", "customer_info").joinWithCassandraTable("test", "shopping_info")
  internalJoin.collect.foreach(println)


  val ids = sc.parallelize(List((1, "joe"), (2, "doris")))
  val localJoin = ids.joinWithCassandraTable("test", "shopping_info");
  localJoin.collect.foreach(println)

  val customers = sc.cassandraTable("test", "customer_info")
    .select("cust_id", "name")
    .as((c: String, n: String) => (c, n))

  val records = sc.cassandraTable("test", "shopping_info")
    .select("cust_id", "date", "shipping_id", "price")
    .as((c: String, d: java.util.Date, s: java.util.UUID, p: Double) => (c, (d, s, p)))

  customers.join(records).collect().foreach(println)

  customers.join(records)
    .map { case (customer, ((name), (date, shopping_id, price))) => (customer, name, date, price, shopping_id) }
    .collect
    .foreach(println)


  customers.join(records)
    .map { case (customer, ((name), (date, shopping_id, price))) => (customer, name, date, price, shopping_id) }
    .saveToCassandra("test","customer_with_shopping",SomeColumns("cust_id", "name","date","price","shipping_id"))
}


