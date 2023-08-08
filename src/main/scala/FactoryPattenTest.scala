trait Computer{
  def ram:String
  def cpu:String
  def hdd:String
}

case class PC(ram:String,cpu:String,hdd:String) extends Computer
case class Laptop(ram:String, cpu:String, hdd:String) extends Computer


object ComputerFactory{
  def apply(compType:String,ram:String,cpu:String,hdd:String): Computer ={

    compType match {
      case "PC" => PC(ram,cpu,hdd)
      case "Laptop" =>Laptop(ram,cpu,hdd)
    }
  }
}


object FactoryPattenTest {
  def main(args: Array[String]): Unit = {
    val pc  = ComputerFactory("PC","16Gb","gz","500Gb")
    println(pc)
    println(pc.cpu)
  }
}
