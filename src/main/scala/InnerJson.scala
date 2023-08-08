import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

import scala.language.postfixOps
object InnerJson {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("Spark SQL basic example")
      .getOrCreate()

    import spark.implicits._

    val dataFile = "/Users/infoobjects/Documents/FIS/Testing/people.json"

    val df = spark.read.json(dataFile)

    val dfFilter = df.filter($"Address" isNotNull)
    dfFilter.show
    print(df.select(col("Address")).take(1)(0).get(0))
    val schema = schema_of_json(df.select(col("Address")).take(1)(0).get(0).toString)
    println(schema)
    val out = dfFilter.select(from_json(col("Address"),schema))
    out.show
//
//    out.write.mode(SaveMode.Overwrite).json("/Users/infoobjects/Documents/FIS/Testing/people_out.json")
  }
}
