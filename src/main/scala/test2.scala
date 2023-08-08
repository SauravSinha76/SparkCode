

object test2 {


//  val appStoreIdSchema: StructType = StructType(Array(
//    StructField("fi_name", StringType, nullable = false)
//    , StructField("app_id", StringType, nullable = false)
//    , StructField("device_os", StringType, nullable = false)
//    , StructField("bank_code", StringType, nullable = false)
//    , StructField("app_key", StringType, nullable = false)
//  ))
//
//  def main(args: Array[String]): Unit = {
//
//    val spark = SparkSession
//      .builder()
//      .master("local[*]")
//      .appName("Spark SQL basic example")
//      .getOrCreate()
//    import spark.implicits._
//
//    //    val config: Config = new Config(args, loggingService)
//    //    val dataFrameService: DataFrameService = new DataFrameService(config, loggingService)
//
//    var idsDF: Dataset[Row] = spark.read
//      .schema(appStoreIdSchema)
//      .option("header", "false")
//      .option("delimiter", "|")
//      .option("escape", "\"")
//      .csv("/Users/infoobjects/Downloads/AppStoreIds.csv")
//
//    idsDF.withColumn("data",explode(upper(col("app_id")))).select($"data.data",$"data.length").show()
//  }
//  case class TemperatureNote(data: String, length: Long, message: String)
//  private val upper:UserDefinedFunction = udf((data:String) => {
//    var list = new ListBuffer[TemperatureNote]
//    for(x <- data.split("\\.")) {
//      list += TemperatureNote(x, x.length, "My message")
//    }
//    list.toList
//  })

  def main(args: Array[String]): Unit = {

  }
}
