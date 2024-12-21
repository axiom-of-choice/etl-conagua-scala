package etl.request

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class HttpRequesterSpec extends AnyFlatSpec with Matchers {
  "HttpRequester" should "fetch data from a given URL" in {
    val url = "https://httpbin.org/get"
    val response = HttpRequester.get(url)
    response should include ("url")
  }
}