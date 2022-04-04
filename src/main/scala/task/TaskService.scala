package task

import com.google.inject.Inject

class TaskService @Inject() (repo: ArrayBufferRepo) {
  def createTodo(plan: Plan): Todo = repo.createTodo(plan)
  def next(task: Task): Option[Task] = repo.next(task)
  def getAllItemsInTodo(): List[Todo] = repo.getAllItemsInTodo()
  def getAllItemsInDoing(): List[Doing] = repo.getAllItemsInDoing()
  def getAllItemsInDone(): List[Done] = repo.getAllItemsInDone()
}
