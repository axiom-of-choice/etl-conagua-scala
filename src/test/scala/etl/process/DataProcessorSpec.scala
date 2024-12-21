package etl.process

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.apache.spark.sql.SparkSession

class DataProcessorSpec extends AnyFlatSpec with Matchers {
  "DataProcessor" should "process data and return a DataFrame" in {
    val spark = SparkSession.builder().appName("Test App").master("local[*]").getOrCreate()
    val inputPath = "./data/raw/test_data.json"
    val df = DataProcessor.process(spark, inputPath)
    df should not be (null)
    df.count() should be > 0L
    spark.stop()
  }
}