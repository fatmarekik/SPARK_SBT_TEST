package com.Datavectis.clustering


import java.io.File
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.Pipeline
import java.util.logging.{Level, Logger}
import org.apache.spark.sql.SparkSession

import org.scalatest.{BeforeAndAfterAll, FunSuite, Matchers,BeforeAndAfterEach}

class TestProg extends FunSuite with BeforeAndAfterEach {

  var spark : SparkSession = _

  override def beforeEach(): Unit = {

    spark = SparkSession.builder().
      master("local")
      .appName("Clustering for Brisbane_CityBike")
      .getOrCreate()

  }
  // testing if the data is successfully loaded
  test("Check existing Data") {
    assert(new File("dataInput/data/Brisbane_CityBike.json").isFile)

  }

  //testing clustering
  test("Checking Clustering") {
    println("test modifcation in evo 2")
    val BrisbaneCityBike = spark.read.json("dataInput/data/Brisbane_CityBike.json")

    val assembler = new VectorAssembler()
      .setInputCols(Array("latitude", "longitude"))
      .setOutputCol("features")
    //  get the model of kmeans clustering
    val kmeans = new KMeans()
      .setK(5)
      .setSeed(1L)
      .setFeaturesCol("features")
      .setPredictionCol("cluster")

    // Building the Pipeline for clustering
    val pipeline = new Pipeline().setStages(Array(assembler, kmeans))

    // Running the clustering pipeline
    val model = pipeline.fit(BrisbaneCityBike.toDF())


    // Adding Cluster Labels to our data
    val clusters = model.transform(BrisbaneCityBike)
    assert(clusters.toDF().columns.size > BrisbaneCityBike.columns.size)
  }

  override def afterEach(): Unit = {
    spark.stop()
  }
}
