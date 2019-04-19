
package com.test.clustering
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.Pipeline
import java.util.logging.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SaveMode

object main {

  //Initialize logging
  val logger = Logger.getLogger(getClass.getName)

  def main(args: Array[String]): Unit = {

    val appName = "Clustering for Brisbane_CityBike"


    val spark = SparkSession.builder()
      .master("local")
      .appName(appName)
      .getOrCreate()

    logger.log(Level.INFO, "Spark context has been initialized with name {0}", spark.sparkContext.appName)


    // Preparing the input data
    val df = spark.read.json(Properties.input_file)

    // Displaying a sample of imported data
    df.show(10, false)
    println(Properties.NumberCluster)
    // Assembling the Vector of input data wish is station positions (latitude and longitude) equivalent to selector
    val assembler = new VectorAssembler()
      .setInputCols(Array("latitude","longitude"))
      .setOutputCol("features")

    // Initializing the KMeans clustering model
    val kmeans = new KMeans()
      .setK(Properties.NumberCluster).setSeed(1L)
      .setFeaturesCol("features")
      .setPredictionCol("cluster")


    // Building the Pipeline for clustering
    // Step 1 : assembling features
    // Step 2 : Running the clustering on data

    val pipeline = new Pipeline().setStages(Array(assembler, kmeans))

    // Running the clustering pipeline
    val model = pipeline.fit(df)


    // Saving the model
    model.write
      .overwrite()
      .save(Properties.MODEL_DIR)

    logger.log(Level.INFO, "Model has been saved in {0}", Properties.MODEL_DIR)

    val clusters = model.transform(df)

    // Saving the dataset with labels and prepare df for dataVis
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