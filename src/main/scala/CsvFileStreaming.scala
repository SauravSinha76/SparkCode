
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.{OutputMode, Trigger}
import org.apache.spark.sql.types._

object CsvFileStreaming {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .master("local[*]")
      .appName("CsvFileStreaming")
      .getOrCreate()

    val schemaExp = StructType(
      StructField("name", StringType, false) ::
        StructField("city", StringType, true) ::
        StructField("country", StringType, true) ::
        StructField("age", IntegerType, true) ::
        StructField("alive", BooleanType, false) :: Nil
    )


    val in = spark.readStream
      .schema(schemaExp)
      .format("csv")
      .option("maxFilesPerTrigger", 1)
      .load("/Users/infoobjects/SparkWorkSpace/WordCount/src/resource/logdata")


    println("Is the query streaming" + in.isStreaming)

    println("Are there any streaming queries?" + spark.streams.active.isEmpty)


    val out = in.
      writeStream.
      format("console").
      option("truncate", false).
      trigger(Trigger.ProcessingTime("5 seconds")).
      queryName("consoleStream").
      outputMode(OutputMode.Append).
      start

    out.awaitTermination()
  }
}
