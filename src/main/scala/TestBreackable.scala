import scala.util.control.Breaks._
object TestBreackable {

  def main(args: Array[String]): Unit = {
    breakable
    {
      for (a <- 1 to 10)
      {
        if (a == 6)

        // terminate the loop when
        // the value of a is equal to 6
          break
        else
          println(a)
      }
    }
  }
}
