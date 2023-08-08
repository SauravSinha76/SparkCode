
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.{OutputMode, Trigger}
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
import java.nio.ByteBuffer

object KafkaStreaming {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder
      .master("local[*]")
      .appName("KafkaStreaming")
      .getOrCreate()


    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("startingOffsets", "earliest")
      .option("subscribe", "test1")
      .load()

    df.printSchema()
    import spark.implicits._
    val df_val = df.selectExpr( "CAST(key AS BINARY)","CAST(value AS STRING)")

    df_val.printSchema()

    val struct = new StructType()
      .add("name", DataTypes.StringType)
      .add("id", DataTypes.LongType)

    val custome_df =  df_val.select(from_json($"value",struct).as("custom"))

    custome_df.printSchema()

    val flat_custome_df = custome_df.selectExpr("custom.id","custom.name")
    val out_df = flat_custome_df.filter($"id" >100)

    df.createOrReplaceTempView("updates")
    val count = spark.sql("Select count(*) from updates")
    println("****************"+count)
    val out = out_df.
      writeStream.
      format("console").
      option("truncate", false).
      trigger(Trigger.ProcessingTime("5 seconds")).
      queryName("consoleStream").
      outputMode(OutputMode.Append).
      start

    def toLong(byte: Array[Byte]): Long ={ ByteBuffer.wrap(byte).getLong}
    def toLongUdf = udf(toLong _)


    val df_with_key = df_val.select(toLongUdf($"key").as("id"),$"value")



    df_with_key.
      writeStream.
      format("console").
      option("truncate", false).
      trigger(Trigger.ProcessingTime("5 seconds")).
      queryName("consoleStreamAppender").
      outputMode(OutputMode.Append).
      start

    spark.streams.awaitAnyTermination()
  }
}
