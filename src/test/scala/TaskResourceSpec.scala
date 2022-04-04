import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

class TaskResourceSpec extends FeatureTest {
  override val server = new EmbeddedHttpServer(twitterServer = new MainServer)

  test("Ping should return Accepted") {
    server.httpGet(path = "/ping", andExpect = Status.Accepted)
  }
}
