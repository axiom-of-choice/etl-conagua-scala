package etl

import etl.request.HttpRequester
import etl.download.DataDownloader
import etl.process.DataProcessor
import etl.load.DataLoader

object Main extends App {
  val (inputUrl, outputPath) = SparkUtils.parseArgs(args)
  val tempFilePath = "./data/raw/data.gz"
  val tempCsvFilePath = "./data/raw/data.json"

  // Request data from endpoint
  val data = HttpRequester.get(inputUrl)

  // Download data to a temporary file
  DataDownloader.download(inputUrl, tempFilePath)

  // DEcompress Gzip file
    DataDownloader.decompressGzip(tempFilePath, tempCsvFilePath)

  // Create Spark session
  val spark = SparkUtils.createSparkSession("ETL Job", isLocal = true)

  // Process data
  val processedData = DataProcessor.process(spark, tempCsvFilePath)

  // Load data to the target system
  DataLoader.load(processedData, outputPath)

  // Testing
  val df = spark.read.parquet(outputPath)
  df.show()

  spark.stop()
}