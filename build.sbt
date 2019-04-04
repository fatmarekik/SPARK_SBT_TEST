
name := "SPARK_SBT_TEST"

version := "0.1"

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.11" % "2.3.0" excludeAll ExclusionRule(organization = "javax.servlet"),
  "org.apache.spark" % "spark-sql_2.11" % "2.3.0" exclude ("org.apache.hadoop","hadoop-yarn-server-web-proxy"),
  "org.apache.hadoop" % "hadoop-common" % "2.7.0" exclude ("org.apache.hadoop","hadoop-yarn-server-web-proxy"),
  "org.apache.spark" % "spark-sql_2.11" % "2.3.0" exclude ("org.apache.hadoop","hadoop-yarn-server-web-proxy"),

  "org.scala-tools" % "maven-scala-plugin" %"2.15.0",
   "org.apache.spark" % "spark-mllib_2.11" % "2.3.0",
  "com.databricks" % "spark-xml_2.11" % "0.4.1",
  "org.scala-lang" % "scala-library" % "2.11.11",
  "com.typesafe" % "config" % "1.3.1"
)







