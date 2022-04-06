package task

import scala.collection.mutable.Map

class ArrayBufferRepo {
  var todoList: Map[Int, Todo] = Map.empty[Int, Todo]
  var doingList: Map[Int, Doing] = Map.empty[Int, Doing]
  var doneList: Map[Int, Done] = Map.empty[Int, Done]

  def getAllItemsInTodo(): List[Todo] = todoList.values.toList
  def getAllItemsInDoing(): List[Doing] = doingList.values.toList
  def getAllItemsInDone(): List[Done] = doneList.values.toList

  def createTodo(plan: Plan): Todo = {
    val lastOption = todoList.lastOption
    val newId = lastOption match {
      case Some(todo) => todo._1 + 1
      case None       => 0
    }
    val newTodo = Todo(newId, plan.detail)
    todoList += (newId -> newTodo)
    newTodo
  }

  def next(task: Task): Option[Task] = {
    val option = task match {
      case _: Todo | _: Doing => fetchTaskFromList(task)
      case _: Done            => None
    }
    option match {
      case Some(task) => moveTaskToNextList(task)
      case None       => None
    }
  }

  def fetchTaskFromList(task: Task): Option[Task] = {
    val (t, targetList) = task match {
      case todo: Todo   => (todo, todoList)
      case doing: Doing => (doing, doingList)
    }
    val target = targetList.get(t.id)
    target match {
      case Some(value) =>
        targetList.remove(value.id)
        Some(value)
      case None => None
    }
  }

  def moveTaskToNextList(task: Task): Option[Task] = {
    val t = task match {
      case Todo(id, detail, _) =>
        doingList += (id -> Doing(id, detail))
        doingList.last._2
      case Doing(id, detail, _) =>
        doneList += (id -> Done(id, detail))
        doneList.last._2
    }
    Some(t)
  }
}

object ArrayBufferRepo {
  def apply(
      todoList: Map[Int, Todo] = Map.empty[Int, Todo],
      doingList: Map[Int, Doing] = Map.empty[Int, Doing],
      doneList: Map[Int, Done] = Map.empty[Int, Done]
  ): ArrayBufferRepo = {
    val ab = new ArrayBufferRepo()
    ab.todoList = todoList
    ab.doingList = doingList
    ab.doneList = doneList
    ab
  }
}
