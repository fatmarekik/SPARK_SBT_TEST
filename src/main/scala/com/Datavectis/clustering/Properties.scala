package com.Datavectis.clustering
import com.typesafe.config._

object Properties {

    val config: Config  = ConfigFactory.load()
   // println(config)

    val INPUT_FILE = config.getString(("Brisbane_CityBike.data"))
    val OUTPUT_DIR = config.getString("output.dir")
    val MODEL_DIR = config.getString("model.dir")
    val NumberCluster = config.getInt("numberCluster")
    val AppName = config.getString( "AppName")
    //val Master = config.getString("Master")

}


