import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object students {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("students").getOrCreate()

    val df = spark.read
      .option("header","true")
      .csv("/Users/infoobjects/SparkWorkSpace/WordCount/src/resource/students.csv")

    df.show()

//    import spark.implicits._
//    df.createOrReplaceTempView("students")
//
//    val result =spark.sql("Select student_name,sum(marks) as total, avg(marks) as Avg from students group by student_name");
//    result.show()

    val result = df.groupBy("student_name").agg(sum("marks")
      .alias("total"),avg("marks").alias("avg_marks"))

    result.show()

    val result2 = result.orderBy(desc("avg_marks"))
    result2.show()
  }
}
