package etl.request

import scalaj.http._

object HttpRequester {
  def get(url: String): String = {
    val response = Http(url).asString
    response.body
  }
}