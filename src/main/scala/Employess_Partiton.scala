import org.apache.spark.sql.{SaveMode, SparkSession}

import scala.collection.mutable.ListBuffer
import scala.util.Random

object Employess_Partiton {

  case class Employee(empId:Int , emp_age:Int, salary:Double)
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().master("local[*]").appName("Employess").getOrCreate()
    val empList = new ListBuffer[Employee]()
    for(index <- 1 to 10000){
      val emp = Employee(index, 20 + Random.nextInt(40), Random.nextDouble()*10000)
      empList += emp
    }

    val emp_df = spark.createDataFrame(empList.toSeq).toDF("empId","emp_age","salary")
    println(f"Number of partition : ${emp_df.rdd.getNumPartitions}")
    println(f"Partitioner ${emp_df.rdd.partitioner}")

    val table_name = "employee"
    val save_path = "/Users/infoobjects/SparkWorkSpace/WordCount/employees"
    emp_df.write.partitionBy("emp_age").format("delta")
      .mode(SaveMode.Overwrite)
      .option("partitionOverwriteMode", "dynamic")
//      .option("overwriteSchema", "true")
      .save(save_path)

    spark.sql("CREATE TABLE " + table_name + " USING DELTA LOCATION '" + save_path + "'")

    spark.sql(f"Select * from ${table_name} where emp_age == 21").show()


    import spark.implicits._
    val df = spark.read.format("delta").load("/Users/infoobjects/SparkWorkSpace/WordCount/employees")
    df.filter($"emp_age"===20)

    val ds = spark.read.format("delta").load("/Users/infoobjects/SparkWorkSpace/WordCount/employees").as[Employee]
    ds.filter(e => e.emp_age == 54)
  }
}
