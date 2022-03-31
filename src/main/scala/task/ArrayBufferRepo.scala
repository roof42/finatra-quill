package task
import scala.collection.mutable.ArrayBuffer

class ArrayTaskRepo {
  val todoList: ArrayBuffer[Todo] = ArrayBuffer.empty[Todo]
  val doingList: ArrayBuffer[Doing] = ArrayBuffer.empty[Doing]
  val doneList: ArrayBuffer[Done] = ArrayBuffer.empty[Done]

  def createTodo(plan: Plan): Int = {
    val id = todoList.last.id + 1
    todoList += Todo(id, plan.detail)
    doingList.sortWith(_.id < _.id)
    id
  }

  def getAllTodos():ArrayBuffer[Todo] = todoList
  def getAllDoings() :ArrayBuffer[Doing]= doingList

  def next(task: Task):Task = {
    task match {
      case Todo(id, detail, _) =>
        val todo = todoList.filter(_.id == id).apply(0)
        doingList += Doing(id, detail)
        todoList.remove(todoList.indexOf(todo))
      case Doing(id, detail, _) =>
        val doing = doingList.filter(_.id == id).apply(0)
        doneList += Done(id, detail)
        doingList.remove(doingList.indexOf(doing))
      case Done(_, _, _) => task
    }
  }
}
