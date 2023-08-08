object FunctionTest {

  def doubler(n:Int): Int = n*2
  def tripler(n:Int): Int =n*3
  def eval(n:Int,f:Int => Int): Int ={
    f(n)
  }

  def fun5 ={
    x:Int => x*x
  }
  def main(args: Array[String]): Unit = {
    println("Test")
    val c = doubler _
    println(c(10))

    println(eval(4,doubler))
    println(eval(4,tripler))

    println(fun5(10))

    var a = 1 to 10
    val p  =a.map(doubler)
    p.map(println)

    val hf = 1 to 10
    val bf  = hf.map(x => x*x)
    bf.map(println)
  }
}
