name := "WordCount"

version := "0.1"

scalaVersion := "2.13.10"

resolvers += Resolver.mavenLocal

libraryDependencies ++= Seq("org.apache.spark" %% "spark-sql" % "3.3.0",
  "org.apache.spark" %% "spark-sql-kafka-0-10" % "3.3.0",
  "org.apache.hadoop" % "hadoop-aws" % "3.3.2",
  "commons-cli" % "commons-cli" % "20040117.000000",
  "io.delta" %% "delta-core" % "2.1.0"
)

