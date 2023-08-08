object Closer_demo {

  def main(args: Array[String]): Unit = {
    println(sum(10)(5))
  }

  val num = 100
  def sum(number:Int): (Int => Int) ={
       (secret:Int) =>number + secret
  }
}
