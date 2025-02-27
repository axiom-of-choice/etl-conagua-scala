package etl

import etl.request.HttpRequester
import etl.download.DataDownloader
import etl.process.{DataProcessor, DataCleaner}
import etl.load.DataLoader

object Main extends App {
  val inputUrl: String = "https://smn.conagua.gob.mx/tools/GUI/webservices/?method=1"
  val tempFilePath : String = "./data/raw/data.gz"
  val tempJsonFilePath: String = "./data/raw/data.json"
  val outputPath: String = "./data/processed/data.parquet"

  // Request data from endpoint
  val data = HttpRequester.get(inputUrl)

  // Download data to a temporary file
  DataDownloader.download(inputUrl, tempFilePath)

  // DEcompress Gzip file
  DataDownloader.decompressGzip(tempFilePath, tempJsonFilePath)

  // Create Spark session
  val spark = SparkUtils.createSparkSession("ETL Job", isLocal = true)

  // Process data
  val processedData = DataProcessor.process(spark, tempJsonFilePath)

  // Load data to the target system
  DataLoader.load(processedData, outputPath)

  // Testing
  val df = spark.read.parquet(outputPath)
  df.show()

  // Remove raw files

  DataCleaner.deleteFileInDirectory(tempFilePath)
  DataCleaner.deleteFileInDirectory(tempJsonFilePath)

  spark.stop()
}