package etl.process

import org.apache.spark.sql.{DataFrame, SparkSession}

object DataProcessor {
  def process(spark: SparkSession, inputPath: String): DataFrame = {
    val df = spark.read.json(inputPath)
    // Add your processing logic here
    df
  }
}