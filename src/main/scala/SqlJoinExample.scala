import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.broadcast

object SqlJoinExample {

  def main(args: Array[String]): Unit = {

    val parentFile= "/Users/infoobjects/Documents/Software/TestData/Pareant.csv"
    val childFile = "/Users/infoobjects/Documents/Software/TestData/Child.csv"

    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("Spark SQL basic example")
      .getOrCreate()

    import spark.implicits._

    println("Before----- "+spark.conf.get("spark.sql.autoBroadcastJoinThreshold"))
//    spark.sql("SET spark.sql.autoBroadcastJoinThreshold = 10")
    println("After----- "+spark.conf.get("spark.sql.autoBroadcastJoinThreshold"))

    val pareantDf = spark.read.option("header","true")
      .csv(parentFile)

    pareantDf.show()

    val childDf = spark.read.option("header","true")
      .csv(childFile)

    val joined = pareantDf.as("parent").join(broadcast(childDf).as("child"),$"parent.Id" === $"child.PareantId","right").filter(pareantDf("Id").isNull)
//    pareantDf.createOrReplaceTempView("parent")
//    childDf.createOrReplaceTempView("child")
//
//    val joined = spark.sql("Select * from parent p right outer join child c ON p.Id == c.PareantId where p.Id is null")

    joined.show()
  }
}
