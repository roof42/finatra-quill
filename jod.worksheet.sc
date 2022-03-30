case class Abc(id: String)
val ab = scala.collection.mutable.ArrayBuffer.empty[Abc]
ab += Abc("a")
ab += Abc("b")
val a = ab.filter(_.id == "a").apply(0)
