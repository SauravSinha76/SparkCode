import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.functions._

object WordCountSample {

  case class Line(line:String)

  def main(args: Array[String]) {
    val logFile = args(0) // Should be some file on your system

    val spark = SparkSession.builder/*.master("local[*]")*/.appName("Simple Application").getOrCreate()
    spark.sparkContext.hadoopConfiguration.set("fs.s3n.impl", "org.apache.hadoop.fs.s3native.NativeS3FileSystem")
    spark.sparkContext.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", "AKIAIW5PRSPR2OGDNZJA")
    spark.sparkContext.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", "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")
    import spark.implicits._
    val logData = spark.read.textFile(logFile).cache()
//    val numAs = logData.filter(line => line.contains("a")).count()
//    Thread.sleep(300*1000)
//    val numBs = logData.filter(line => line.contains("b")).count()
//    println(s"Lines with a: $numAs, Lines with b: $numBs")

    val splitData = logData.flatMap(_.split("[\\s+|\\t]")).filter(isWord(_)).map(word => (word,1)).rdd
    val finalCount = splitData.reduceByKey((a,b) => a + b)
    val output = finalCount.toDS().orderBy(desc("_2")).cache()
    output.show()
    output.coalesce(1).write.mode(SaveMode.Overwrite).csv(args(1))
    spark.stop()
  }

  def getWord(word: String): Option[(String, Int)] ={
    if(word != null && word.trim.length > 1){
      Some(word.trim , 1)
    }
    else None
  }

  def isWord(word: String) : Boolean ={
    if(word != null && word.trim.length > 1){
      true
    }
    else false
  }
}
