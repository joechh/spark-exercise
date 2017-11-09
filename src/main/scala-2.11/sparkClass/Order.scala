package sparkClass

/**
  * Created by joechh on 2017/1/12.
  */

case class Order(time: java.sql.Timestamp, orderId: Long, clientId: Long,
                 symbol: String, amount: Int, price: Double, buy: Boolean)
