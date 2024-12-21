package etl.load

import org.apache.spark.sql.DataFrame

object DataLoader {
  def load(df: DataFrame, outputPath: String): Unit = {
    df.write.mode("overwrite").parquet(outputPath)
  }
}