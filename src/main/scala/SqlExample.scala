import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col

object SqlExample {

  def main(args: Array[String]): Unit = {


    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("Spark SQL basic example")
      .getOrCreate()

    // For implicit conversions like converting RDDs to DataFrames

    val dataFile = "/Users/infoobjects/Documents/Software/spark-3.3.0-bin-hadoop3/examples/src/main/resources/people.json"

    val df = spark.read.json(dataFile)

    df.printSchema()

    val df1 = df.selectExpr("name","cast(age as string) age")
    df1.printSchema()



    import spark.implicits._
    df.show()
    df.printSchema()
    df.select("name").show()
    df.select(col("name")).show()
    df.select($"name", $"age" + 1).show()
    df.filter($"age" > 21).show()
    df.groupBy("age").count().show()

    df.createOrReplaceTempView("people")

    val sqlDF = spark.sql("SELECT * FROM people")
    sqlDF.show()
    sqlDF.explain("extended")
  }
}
