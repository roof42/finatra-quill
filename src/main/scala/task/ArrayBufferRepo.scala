package task

import scala.collection.mutable.Map
import scala.util.Try

class ArrayBufferRepo {
  val todoList: Map[Int, Todo] = Map.empty[Int, Todo]
  val doingList: Map[Int, Doing] = Map.empty[Int, Doing]
  val doneList: Map[Int, Done] = Map.empty[Int, Done]

  def getAllTodos(): Map[Int, Todo] = todoList
  def getAllDoings(): Map[Int, Doing] = doingList

  def createTodo(plan: Plan): Todo = {
    val newTodo = todoList.lastOption match {
      case Some(value) => Todo(value._1.intValue() + 1, plan.detail)
      case None        => Todo(0, plan.detail)
    }
    todoList += (newTodo.id -> newTodo)
    newTodo
  }

  def next(task: Task): Option[Task] = {
    task match {
      case Todo(id, _, _) =>
        moveTaskToDoing(id)
      case Doing(id, _, _) =>
        moveTaskToDone(id)
      case _ => Some(task.asInstanceOf[Done])
    }
  }

  private def moveTaskToDone(id: Int): Option[Done] = {
    val done = doingList.get(id) match {
      case Some(doing) =>
        doingList.remove(doing.id)
        Some(Done(doing.id, doing.detail))
      case None => None
    }
    done match {
      case Some(done) =>
        doneList += (done.id -> done)
        Some(done)
      case None => None
    }
  }

  private def moveTaskToDoing(id: Int): Option[Doing] = {
    val doing = todoList.get(id) match {
      case Some(todo) =>
        todoList.remove(todo.id)
        Some(Doing(todo.id, todo.detail))
      case None => None
    }
    doing match {
      case Some(doing) =>
        doingList += (doing.id -> doing)
        Some(doing)
      case None => None
    }
  }
}
