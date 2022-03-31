package task

import scala.collection.mutable.ArrayBuffer

class ArrayBufferRepo {
  val todoList: ArrayBuffer[Todo] = ArrayBuffer.empty[Todo]
  val doingList: ArrayBuffer[Doing] = ArrayBuffer.empty[Doing]
  val doneList: ArrayBuffer[Done] = ArrayBuffer.empty[Done]

  def getAllTodos():ArrayBuffer[Todo] = todoList
  def getAllDoings() :ArrayBuffer[Doing]= doingList

  def createTodo(plan: Plan): Todo = {
    val newTodo = todoList.lastOption match {
      case Some(todo) => Todo(todo.id+1, plan.detail)
      case None => Todo(1, plan.detail)
    }
    todoList +=newTodo
    newTodo
  }


  def next(task: Task):Task = {
    task match {
      case Todo(id, detail, _) =>
        val todo = todoList.filter(_.id == id).apply(0)
        val doing = Doing(id, detail)
        doingList += doing
        todoList.remove(todoList.indexOf(todo))
        doing
      case Doing(id, detail, _) =>
        val doing = doingList.filter(_.id == id).apply(0)
        val done = Done(id, detail)
        doneList += done
        doingList.remove(doingList.indexOf(doing))
        done
      case Done(_, _, _) => task.asInstanceOf[Done]
    }
  }
}
