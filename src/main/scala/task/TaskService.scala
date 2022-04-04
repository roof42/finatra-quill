package task

import com.google.inject.Inject

class TaskService @Inject() (repo: ArrayBufferRepo) {
  def createTodo(plan: Plan): Todo = repo.createTodo(plan)
  def next(task: Task): Option[Task] = repo.next(task)
  def getAllTodos(): List[Todo] = repo.getAllTodos()
  def getAllDoings(): List[Doing] = repo.getAllDoings()
}
