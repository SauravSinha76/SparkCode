import org.apache.spark.sql.SparkSession

object SqlTopSalariedEmployes {

  case class Employee(department: String, name: String, salary: Long)

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("Spark SQL basic example")
      .getOrCreate()

    import spark.implicits._

    val dataFile = "/Users/infoobjects/SparkWorkSpace/WordCount/src/resource/SampleData.csv"

    val employees = spark.sparkContext.textFile(dataFile)
      .map(_.split(","))
      .map(attribute => Employee(attribute(0), attribute(1), attribute(2).trim.toLong))
      .toDF

    employees.show()

    employees.createOrReplaceTempView("employee")

    val rankedEmployee = spark.sql("Select *  from" +
      "(Select *,dense_rank() over(partition by department order by salary desc)as r from employee) as tab where r =2")


    rankedEmployee.show()

//    val topSalayEmploye = rankedEmployee.filter($"r"=== 3).select($"department",$"name",$"salary")
//
//    topSalayEmploye.show()
  }
}
