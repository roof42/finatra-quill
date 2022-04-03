package task

package task
import org.scalatest.flatspec.AnyFlatSpec
import scala.collection.mutable.Map

class ArrayBufferRepoSpec extends AnyFlatSpec {
  it should "create new todo with id = 0" in {
    val plan = Plan("Buy banana")
    val repo = new ArrayBufferRepo()
    val todo = repo.createTodo(plan)
    assert(todo.isInstanceOf[Todo])
    assert(todo.id == 0)
  }

  it should "move todo to doing with same id" in {
    val plan = Plan("Buy banana")
    val repo = new ArrayBufferRepo(
      todoList = Map(0 -> Todo(id = 0, detail = "buy banana"))
    )
    val todo = repo.getAllTodos().get(0) match {
      case Some(value) => value
      case None        => ???
    }

    val doing = repo.next(todo)

    assert(doing.get.id == 0)
  }

  it should "has empty todoList after move 1 item out" in {
    val plan = Plan("Buy banana")
    val repo = new ArrayBufferRepo(
      todoList = Map(0 -> Todo(id = 0, detail = "buy banana"))
    )
    val todo = repo.getAllTodos().get(0) match {
      case Some(value) => value
      case None        => ???
    }

    val doing = repo.next(todo)

    assert(repo.getAllTodos().size == 0)
    assert(repo.getAllDoings().size == 1)
  }
}
