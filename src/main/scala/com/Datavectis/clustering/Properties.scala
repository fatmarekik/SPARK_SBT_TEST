package com.Datavectis.clustering
import com.typesafe.config._

object Properties {

    val config: Config  = ConfigFactory.load()

    val INPUT_FILE = config.getString(("input.data"))
    val OUTPUT_DIR = config.getString("output.dir")
    val MODEL_DIR = config.getString("model.dir")
    val NumberCluster = config.getInt("numberCluster")
    val AppName = config.getString( "appName")
    val Master = config.getString("master")


}


