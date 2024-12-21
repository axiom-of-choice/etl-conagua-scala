package etl

import org.apache.spark.sql.SparkSession

package object SparkUtils {
  def createSparkSession(appName: String, isLocal: Boolean): SparkSession = {
    if (isLocal) {
      SparkSession
        .builder()
        .config("spark.sql.caseSensitive", value = true)
        .config("spark.sql.session.timeZone", value = "UTC")
        .config("spark.driver.memory", value = "8G")
        .appName(appName)
        .master("local[*]")
        .getOrCreate()
    } else {
      SparkSession
        .builder()
        .config("spark.sql.caseSensitive", value = true)
        .config("spark.sql.session.timeZone", value = "UTC")
        .appName(appName)
        .getOrCreate()
    }
  }

  def parseArgs(args: Array[String]): (String, String) = {
    val parsedArgs = args.sliding(2, 2).collect {
      case Array(argName, argValue) => argName -> argValue
    }.toMap

    val inputPath = parsedArgs.getOrElse("--endpoint-url", throw new IllegalArgumentException("Missing --endpoint-url argument"))
    val outputPath = parsedArgs.getOrElse("--output-path", throw new IllegalArgumentException("Missing --output-path argument"))

    println(s"input path: ${inputPath}")
    println(s"output path: ${outputPath}")

    (inputPath, outputPath)
  }
}