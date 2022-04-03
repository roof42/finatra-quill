package task

import scala.collection.mutable.Map
import scala.util.Try

case class ArrayBufferRepo(
    val todoList: Map[Int, Todo] = Map.empty[Int, Todo],
    val doingList: Map[Int, Doing] = Map.empty[Int, Doing],
    val doneList: Map[Int, Done] = Map.empty[Int, Done]
) {
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
      case t: Todo =>
        val todo = fetchTaskFromList(t)
        val doing = Doing(todo.get.id, todo.get.detail)
        doingList += (doing.id -> doing)
        Some(doing)
      case d: Doing =>
        val doing = fetchTaskFromList(d)
        val done = Done(doing.get.id, doing.get.detail)
        doneList += (done.id -> done)
        Some(done)
      case _ => Some(task.asInstanceOf[Done])
    }
  }

  def fetchTaskFromList(task: Task) = {
    val targetList = task match {
      case _: Todo  => todoList
      case _: Doing => doingList
    }
    targetList.get(task.id) match {
      case Some(value) =>
        todoList.remove(task.id)
        Some(value)
      case None => None
    }
  }
}
