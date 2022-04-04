package task

import java.time.Instant

trait Task {
  val id: Int
  val detail: String
  val postedAt: Instant
}

case class Todo(id: Int, detail: String, postedAt: Instant = Instant.now())
    extends Task

case class Doing(id: Int, detail: String, postedAt: Instant = Instant.now())
    extends Task

case class Done(id: Int, detail: String, postedAt: Instant = Instant.now())
    extends Task

case class Plan(detail: String, postedAt: Instant = Instant.now())


object Doing {
  def ->:(td: Option[Todo]): Option[Doing]  = td match {
    case Some(todo) => Some(Doing(todo.id, todo.detail, todo.postedAt))
    case None => None
  }
}