package task

import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.finagle.http.Status
import com.twitter.inject.server.FeatureTest
import scala.collection.immutable.ListMap

class HelloControllerSpec extends FeatureTest {
  override val server = new EmbeddedHttpServer(
    twitterServer = new MainServer
    // globalFlags = ListMap(com.foo.bar.someGlobalFlag -> "a value")
  )

  test("ExampleServer#perform feature") {
    // any read of the `com.foo.bar.someGlobalFlag` value within the server will be "a value"
    server.httpGet(path = "/", andExpect = Status.Ok)
  }
}
