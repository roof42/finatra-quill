import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

class TaskResourceSpec extends FeatureTest {
  override val server = new EmbeddedHttpServer(twitterServer = new MainServer)

  test("Ping should return Accepted") {
    server.httpGet(path = "/ping", andExpect = Status.Accepted)
  }

  test("Create new todo") {
    server.httpPost(
      path = "/todos",
      postBody = """
          |{
          |"detail":"shekhar"
          |}
        """.stripMargin,
      andExpect = Status.Created
    )
  }

  test("Get all todo in list") {
    val json = server.httpGet(path = "/todos", andExpect = Status.Ok)
    assert(
      json.contentString.toString().split(',').apply(0).contains("0") == true
    )
  }

  test("Move item from todo to doing") {
    server.httpPost(
      "/todos/next",
      postBody = """
      |{
      |"id":0, 
      |"detail":"some detail",
      |"postedAt":""
      |}
      """.stripMargin,
      andExpect = Status.Accepted
    )
    server.httpGet(path = "/todos", andExpect = Status.Ok, withJsonBody = "[]")
    val json = server.httpGet(path = "/doings", andExpect = Status.Ok)
    assert(
      json.contentString.toString().split(',').apply(0).contains("0") == true
    )
  }

  test("Move item from doing to done") {
    server.httpPost(
      "/doing/next",
      postBody = """
      |{
      |"id":0,
      |"detail":"some detail",
      |"postedAt":""
      |}
      """.stripMargin,
      andExpect = Status.Accepted
    )
    server.httpGet(path = "/doings", andExpect = Status.Ok, withJsonBody = "[]")
    val json = server.httpGet(path = "/dones", andExpect = Status.Ok)
    assert(
      json.contentString.toString().split(',').apply(0).contains("0") == true
    )
  }

}
