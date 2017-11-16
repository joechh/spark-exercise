trait Car {
  val brand: String
}

trait FlyAble {
  val equipment: String
}

class BMW extends Car with FlyAble {
  override val brand: String = "BMW-X7"
  override val equipment: String = "wing"
}

val car = new BMW
car.brand
car.equipment

val car2 = new BMW
car == car2

object JoeCar extends Car with FlyAble {
  override val brand: String = "BMW-X7"
  override val equipment: String = "wing"
}

val objectCar1 = JoeCar
val objectCar2 = JoeCar


