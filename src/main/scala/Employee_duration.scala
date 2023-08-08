import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

object Employee_duration {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().master("local[*]").appName("Emp").getOrCreate()

    val swap = spark.read.option("header","true")csv("/Users/infoobjects/PycharmProjects/SimplePySparkApp/resources/swap.csv")

    val window_fn = Window.partitionBy("empid").orderBy("swap_time")

    val res = swap.withColumn("last_swap",lag("swap_time",1).over(window_fn))
    res.show()

    val res_2 = res.withColumn("duration", when(col("status") === "Logout",
      to_timestamp(col("swap_time")).cast("long") - to_timestamp(col("last_swap")).cast("long")))
    res_2.show()

  }
}
