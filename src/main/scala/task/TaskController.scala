package task
import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request
import com.google.inject.Inject
import task._
class TaskController @Inject() (repo: ArrayTaskRepo) extends Controller {
  get("/ping") { request: Request => response.accepted("pong") }
  get("/todos") { request: Request => repo.getAllTodos() }
  post("/todos") { todo: Todo => repo.createTodo(todo) }
  post("/todos/next") { todo: Todo => repo.next(todo) }
  get("/doings") { request: Request => repo.getAllDoings() }
  post("/doing/next") { doing: Doing => repo.next(doing) }
}
