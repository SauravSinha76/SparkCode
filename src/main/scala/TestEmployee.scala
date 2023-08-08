

object TestEmployee {
  case class Employees(name:String, department:String, salary: Double)
  def main(args: Array[String]): Unit = {


    val employees = List(
      Employees("AAAAA", "IT", 1200),
      Employees("BBBB", "IT", 3000),
      Employees("CCC", "IT", 40000),
      Employees("DDD", "SALE", 1100),
      Employees("EEE", "Ops", 1100),
    )
    val newList = employees.toStream.map(e => if (e.department == "IT") e.copy(salary = double(e.salary)) else e)
    newList.foreach(println)


    val numbers = List(1.0,2,3)
    val dob_number= numbers.map(double)
    dob_number.foreach(println)
  }

  def double(value: Double): Double = value*2
}


