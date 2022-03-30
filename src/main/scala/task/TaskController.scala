package task
import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request
class TaskController extends Controller {
  get("/ping") { request: Request => response.accepted("pong") }
}
