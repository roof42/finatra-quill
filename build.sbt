scalaVersion := "2.13.8"
lazy val versions = new {
  val finatra = "21.2.0"
  val logback = "1.1.3"
  val guicetestlib = "5.1.0"
}

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  "Twitter Maven" at "https://maven.twttr.com"
)

libraryDependencies += "com.twitter" %% "finatra-http" % versions.finatra
libraryDependencies += "ch.qos.logback" % "logback-classic" % versions.logback
libraryDependencies += "io.getquill" %% "quill-jdbc" % "3.16.2"
libraryDependencies += "org.postgresql" % "postgresql" % "42.3.3"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test"
