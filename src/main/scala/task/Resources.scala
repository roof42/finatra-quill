package task
import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request
import com.google.inject.Inject
import task._
class Resources @Inject() (repo: ArrayBufferRepo) extends Controller {
  get("/ping") { _: Request => response.accepted("pong") }

  get("/todos") { _: Request => repo.getAllTodos() }

  post("/todos") { plan: Plan =>
    val todo = repo.createTodo(plan)
    response.created(s"Todo was created with id = ${todo.id}")
  }

  post("/todos/next") { todo: Todo =>
    println(s"About to move ${todo.id} - ${todo.detail}")
    repo.next(todo) match {
      case Some(doing) =>
        response.created(
          s"Task number ${doing.asInstanceOf[Doing].id} was moved to doing list"
        )
      case None => response.badRequest("Invalid id")
    }
  }

  get("/doings") { _: Request => repo.getAllDoings() }

  post("/doing/next") { doing: Doing => repo.next(doing) }
}
