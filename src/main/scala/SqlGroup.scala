import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.functions._

object SqlGroup {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
//      .master("local[*]")
      .appName("Spark SQL basic example")
      .getOrCreate()

    // For implicit conversions like converting RDDs to DataFrames
    import spark.implicits._

    val dataFile = "/Users/infoobjects/Documents/FIS/Testing/GroupBy"

    val df = spark.read.option("header","true")
      .csv(dataFile)

    df.select($"dealid",$"hour",$"frame",struct($"placementid",$"soldqty").as("Placement")).show()
    val transform = df.select($"dealid",$"hour",$"frame",struct($"placementid",$"soldqty").as("Placement"))
      .groupBy($"dealid",$"hour",$"frame").agg(collect_list($"Placement"))
    //    df.show()
    //    df.printSchema()
    //    df.select("name").show()
    //    df.select($"name", $"age" + 1).show()
    //    df.filter($"age" > 21).show()
    //    df.groupBy("age").count().show()


    transform.show()

    transform.write.mode(SaveMode.Overwrite).json("/Users/infoobjects/Documents/FIS/Testing/groupby_out.json")
  }
}
