package etl.process

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.apache.spark.sql.SparkSession
import java.io.{File}
import org.scalatest.BeforeAndAfterAll

class DataProcessorSpec extends AnyFlatSpec with Matchers with BeforeAndAfterAll {
  val inputPath = "./data/raw/test_data.json"

  override def afterAll(): Unit = {
    // Clean up test files
    new File(inputPath).delete()
  }

  "DataProcessor" should "process data and return a DataFrame" in {
    val spark = SparkSession.builder().appName("Test App").master("local[*]").getOrCreate()
    val df = DataProcessor.process(spark, inputPath)
    df should not be (null)
    df.count() should be > 0L
    spark.stop()
  }
}