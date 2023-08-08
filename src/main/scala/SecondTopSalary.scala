import org.apache.spark.sql.{SaveMode, SparkSession}

object SecondTopSalary {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
      .master("local")
      .appName("TopSalary")
      .getOrCreate()

    import spark.implicits._
    val employees = spark.read.json("/Users/infoobjects/SparkWorkSpace/WordCount/src/resource/employees.json")

    employees.show()

    employees.createOrReplaceTempView("employee")

    val rankedEmployees= spark.sql("Select *,row_number() over(order by salary desc) as r from employee")
    rankedEmployees.show()
    println(rankedEmployees.schema)

    val secondTopSalary = rankedEmployees.filter($"r"=== 2).select($"id",$"name",$"salary",$"r")
    secondTopSalary.show()

    secondTopSalary.write.mode(SaveMode.Overwrite).csv("/Users/infoobjects/SparkWorkSpace/WordCount/src/resource/out/")
  }
}
