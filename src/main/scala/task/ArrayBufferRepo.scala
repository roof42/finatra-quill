package task

import scala.collection.mutable.Map

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
    doingList.get(id) match {
      case Some(value) =>
        val done = Done(value.id, value.detail)
        doneList += (done.id -> done)
        doingList.remove(value.id)
        Some(done)
      case None => None
    }
  }

  private def moveTaskToDoing(id: Int): Option[Doing] = {
    todoList.get(id) match {
      case Some(value) =>
        val doing = Doing(value.id, value.detail)
        doingList += (doing.id -> doing)
        todoList.remove(value.id)
        Some(doing)
      case None => None
    }
  }
}
