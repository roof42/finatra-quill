package task
import scala.collection.mutable.ArrayBuffer

class ArrayTaskRepo {
  val todos: ArrayBuffer[Todo] = ArrayBuffer.empty[Todo]
  val doings: ArrayBuffer[Doing] = ArrayBuffer.empty[Doing]
  val dones: ArrayBuffer[Done] = ArrayBuffer.empty[Done]

  def createTodo(newTodo: Todo) = {
    todos += newTodo
  }

  def getAllTodos() = todos
  def getAllDoings() = doings

  def next(task: Task) = {
    task match {
      case Todo(id, detail, _) => {
        val todo = todos.filter(_.id == id).apply(0)
        doings += Doing(id, detail)
      }
      case Doing(id, detail, _) => {
        val doing = doings.filter(_.id == id).apply(0)
        dones += Done(id, detail)
      }
      case Done(id, detail, _) => { Done(id, detail, _) }
    }
  }
}
