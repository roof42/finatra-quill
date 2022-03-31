package task

import java.time.Instant

trait Id {
  val id: Int
}

trait Task {
  val detail: String
  val postedAt: Instant
}

case class Todo(id: Int, detail: String, postedAt: Instant = Instant.now())
    extends Task
    with Id
case class Doing(id: Int, detail: String, postedAt: Instant = Instant.now())
    extends Task
    with Id
case class Done(id: Int, detail: String, postedAt: Instant = Instant.now())
    extends Task
    with Id

case class Plan(detail: String, postedAt: Instant = Instant.now()) extends Task

