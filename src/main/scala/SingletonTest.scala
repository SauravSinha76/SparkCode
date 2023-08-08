
object person{
  def apply(name: String, age: Int): person = new person(name,age)

  private val eyes =2
  def canFly = false
}

class person(name:String,age:Int){
  def doubleSalary(salaray:Double) = salaray*2
  def getEye:Int = person.eyes
}
object SingletonTest {

  def main(args: Array[String]): Unit = {
    val p1 = person("Saurav",30)
    println(p1)
    println(p1.getEye)
  }


}
