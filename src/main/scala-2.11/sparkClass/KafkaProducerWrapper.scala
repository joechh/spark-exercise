package sparkClass

import kafka.producer.{KeyedMessage, Producer, ProducerConfig}

/**
  * Created by joechh on 2017/1/9.
  */
case class KafkaProducerWrapper(brokerList: String) {
  val producerProps = {
    val prop = new java.util.Properties
    prop.put("metadata.broker.list", brokerList)
    prop.put("serializer.class", "kafka.serializer.StringEncoder")
    prop.put("producer.type", "async")
    prop
  }



  val p = new Producer[String, String](new ProducerConfig(producerProps))

  def send(topic: String, key: String, value: String) {
    p.send(new KeyedMessage(topic, key, value))
  }

  def send(topic: String, value: String) {
    p.send(new KeyedMessage[String, String](topic, value))
  }

  def close() {
    p.close()
  }
}

object KafkaProducerWrapper {
  var brokerList = ""
  lazy val getInstance = new KafkaProducerWrapper(brokerList)
}

object SingletonKafkaTest extends App {
  KafkaProducerWrapper.brokerList = "172.16.2.26:9092,172.16.2.27:9092,172.16.2.28:9092"
  val producer = KafkaProducerWrapper.getInstance
  producer.send("dev", "this is a test")
  println("haha")
  producer.close()

}