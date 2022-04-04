import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest
import scala.collection.immutable.ListMap

class HelloControllerSpec extends FeatureTest {
  override val server = new EmbeddedHttpServer(twitterServer = new MainServer)

  test("ExampleServer#perform feature") {
    server.httpGet(path = "/ping", andExpect = Status.Accepted)
  }
}
