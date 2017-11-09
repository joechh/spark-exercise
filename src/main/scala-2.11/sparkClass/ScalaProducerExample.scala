package sparkClass

import java.util.{Date, Properties}

import kafka.producer.{KeyedMessage, Producer, ProducerConfig}

import scala.util.Random

object ScalaProducerExample extends App {
  val events = 100;
  val topic = "testTopic"
  val rnd = new Random()
  val props = new Properties()
  props.put("metadata.broker.list", "192.168.56.1:9092")
  props.put("serializer.class", "kafka.serializer.StringEncoder")
  props.put("producer.type", "async")


  val config = new ProducerConfig(props)
  val producer = new Producer[String, String](config)
  val t = System.currentTimeMillis()
  for (nEvents <- Range(0, events)) {
    val runtime = new Date().getTime();
    val ip = "192.168.2." + rnd.nextInt(5);
    //val msg = runtime + "," + nEvents + ",www.example.com," + ip;
    val msg = ip
    val data = new KeyedMessage[String, String](topic, ip, msg);
    producer.send(data);
  }

  System.out.println("sent per second: " + events * 1000 / (System.currentTimeMillis() - t));
  producer.close();
}