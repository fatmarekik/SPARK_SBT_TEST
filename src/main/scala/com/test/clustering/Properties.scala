package com.test.clustering
import com.typesafe.config._

object Properties {

    val config: Config  = ConfigFactory.load()
    println(config)

    val input_file = config.getString(("uu.data"))
    val OUTPUT_DIR = config.getString("output.dir")
    val MODEL_DIR = config.getString("model.dir")
   val NumberCluster = config.getInt("numberCluster")

}


