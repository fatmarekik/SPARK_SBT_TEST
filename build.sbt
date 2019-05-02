
name := "SPARK_SBT_TEST"

version := "0.1"

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  // testing
  "org.scalatest" %% "scalatest" % "2.2.4" % "provided",
  // spark modules and cores
  "org.apache.spark" % "spark-core_2.11" % "2.3.0" % "provided",
  "org.apache.spark" % "spark-sql_2.11" % "2.3.0" % "provided" ,
  "org.apache.spark" % "spark-mllib_2.11" % "2.3.0"  % "provided" ,
  "com.databricks" % "spark-xml_2.11" % "0.4.1" % "provided" ,
  // scala tools
  "org.scala-tools" % "maven-scala-plugin" %"2.15.0"  % "provided",
  "org.scala-lang" % "scala-library" % "2.11.11"  % "provided" ,
  "com.typesafe" % "config" % "1.2.1" 
    
)



fork in run := true







