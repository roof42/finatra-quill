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
