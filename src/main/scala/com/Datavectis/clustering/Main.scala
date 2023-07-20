
package com.Datavectis.clustering
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.Pipeline
import java.util.logging.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SaveMode

object Main {

  println("This is the main function in master")
  println("evo1")
  println("corr evo1")
  println("corr 3 :creation de la branch evo 2")
  //Initialize logging
  val logger = Logger.getLogger(getClass.getName)

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .master(Properties.Master)
      .appName(Properties.AppName)
      .getOrCreate()

    logger.log(Level.INFO, "Spark context has been initialized with name {0}", spark.sparkContext.appName)



    logger.log(Level.INFO, "Preaparing the input data")
    val df = spark.read.json(Properties.INPUT_FILE)

    logger.log(Level.INFO, " Assembling the Vector of input data which is station positions (latitude and longitude) equivalent to selector")
    val assembler = new VectorAssembler()
      .setInputCols(Array("latitude","longitude"))
      .setOutputCol("features")


    logger.log(Level.INFO, "Initializing the KMeans clustering model")
    val kmeans = new KMeans()
      .setK(Properties.NumberCluster).setSeed(1L)
      .setFeaturesCol("features")
      .setPredictionCol("cluster")


    // Step 1 : assembling features
    // Step 2 : Running the clustering on data
    logger.log(Level.INFO, "Building the Pipeline for clustering with {0} clusters" ,Properties.NumberCluster )
    val pipeline = new Pipeline().setStages(Array(assembler, kmeans))

    // Running the clustering pipeline
    logger.log(Level.INFO, "Running the clustering pipeline")
    val model = pipeline.fit(df)


    // Saving the model
    logger.log(Level.INFO, "Saving the model")
    model.write
      .overwrite()
      .save(Properties.MODEL_DIR)

    logger.log(Level.INFO, "Model has been saved in {0}", Properties.MODEL_DIR)

    val clusters = model.transform(df)

    // Saving the dataset with labels and prepare df for dataVis
    logger.log(Level.INFO, "Saving the  datset with labels and prepare it for Data Visualisation")
    clusters.drop("features")
      .repartition(1)
      .write
      .mode(SaveMode.Overwrite)
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .option("delimiter", ";")
      .save(Properties.OUTPUT_DIR)

    logger.log(Level.INFO, "Clustered data has been saved in {0}", Properties.OUTPUT_DIR)

    // Displaying a sample of data with labels
    clusters.drop("features").show(10, false)

    spark.close()

  }
}