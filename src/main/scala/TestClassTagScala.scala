import scala.reflect.ClassTag

object TestClassTagScala {

  def main(args: Array[String]): Unit = {
    class Animal{
      override def toString: String = "I am Animal"
    }

    val a = Nil
    print(a)
    val myMap:collection.Map[String, Any] = Map("Number" -> 1, "Greeting"->"Hello World",
      "Animal" -> new Animal)

    val number:Option[Int] = getValueFromMap[Int]("Number",myMap)
    println("number  is " + number)

    val greeting:Option[String] = getValueFromMap[String]("Greeting",myMap)
    println("Greeting is "+greeting)

    val greetingInt:Option[Int] = getValueFromMap[Int]("Greeting",myMap)
    println("Greeting is "+greetingInt)
  }

  def getValueFromMap[T: ClassTag](key:String, dataMap: collection.Map[String,Any]): Option[T] =
    dataMap.get(key) match {
      case Some(value:T) => Some(value)
      case _ => None
    }
}
