package etl.download

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import java.io.{File, FileInputStream, InputStreamReader, BufferedReader}
import org.scalatest.BeforeAndAfterAll


class DataDownloaderSpec extends AnyFlatSpec with Matchers with BeforeAndAfterAll {

  val gzFilePath = "./data/raw/test_data.gz"
  val outputFilePath = "./data/raw/test_data.json"


  override def afterAll(): Unit = {
    // Clean up test files
    new File(gzFilePath).delete()
  }

  "DataDownloader" should "download data from a given URL" in {
    val url = "https://smn.conagua.gob.mx/tools/GUI/webservices/?method=1"
    val outputPath = "./data/raw/test_data.gz"
    DataDownloader.download(url, outputPath)
    new File(outputPath).exists() should be (true)
  }

  it should "decompress a Gzip file" in {

    DataDownloader.decompressGzip(gzFilePath, outputFilePath)
    val outputFile = new File(outputFilePath)
    outputFile.exists() should be (true)

    // Verify the contents of the decompressed file
    val reader = new BufferedReader(new InputStreamReader(new FileInputStream(outputFile)))
    val firstLine = reader.readLine()
    reader.close()
    firstLine should not be (null)
  }
}