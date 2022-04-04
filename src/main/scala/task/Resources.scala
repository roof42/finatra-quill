package task
import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request
import com.google.inject.Inject

class Resources @Inject() (service: TaskService) extends Controller {
  get("/ping") { _: Request => response.accepted("pong") }

  get("/todos") { _: Request => service.getAllItemsInTodo() }

  post("/todos") { plan: Plan =>
    val todo = service.createTodo(plan)
    response.created(s"Todo was created with id = ${todo.id}")
  }

  post("/todos/next") { todo: Todo =>
    service.next(todo) match {
      case Some(doing) =>
        response.created(
          s"Task number ${doing.asInstanceOf[Doing].id} was moved to doing list"
        )
      case None => response.badRequest("Invalid id")
    }
  }

  get("/doings") { _: Request => service.getAllItemsInDoing() }

  post("/doing/next") { doing: Doing => service.next(doing) }
}
