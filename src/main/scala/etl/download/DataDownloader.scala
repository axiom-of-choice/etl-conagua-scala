package etl.download

import java.io._
import java.net.URL
import java.util.zip.GZIPInputStream

object DataDownloader {
  def download(url: String, outputPath: String): Unit = {
    val in = new BufferedInputStream(new URL(url).openStream())
    val out = new FileOutputStream(outputPath)
    val buffer = new Array[Byte](1024)
    Stream.continually(in.read(buffer)).takeWhile(_ != -1).foreach(out.write(buffer, 0, _))
    in.close()
    out.close()
  }

  def decompressGzip(gzFilePath: String, outputFilePath: String): Unit = {
    val buffer = new Array[Byte](1024)
    val gzInputStream = new GZIPInputStream(new FileInputStream(gzFilePath))
    val outputStream = new FileOutputStream(outputFilePath)

    Stream.continually(gzInputStream.read(buffer)).takeWhile(_ != -1).foreach(outputStream.write(buffer, 0, _))

    gzInputStream.close()
    outputStream.close()
  }
}