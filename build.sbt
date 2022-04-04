import sbt.plugins.DependencyTreeKeys.dependencyDotFile

scalaVersion := "2.13.8"
//render dot file to `./dependencies.dot`
lazy val versions = new {

  val releaseVersion = "22.4.0-SNAPSHOT"
  // All Twitter library releases are date versioned as YY.MM.patch
  val twLibVersion = releaseVersion
  val guice = "4.2.3"
  val jodaConvert = "1.5"
  val jodaTime = "2.10.10"
  val nscalaTime = "2.22.0"
  val scalaGuice = "4.2.11"
  val scalaTest = "3.1.2"
  val slf4j = "1.7.30"

}

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("test")
)

libraryDependencies ++= Seq(
  "com.twitter" %% "finatra-http-server" % "22.3.0",
  "com.google.inject" % "guice" % versions.guice,
  "com.google.inject.extensions" % "guice-assistedinject" % versions.guice,
  "com.google.inject.extensions" % "guice-multibindings" % versions.guice,
  "com.twitter" %% "util-app" % versions.twLibVersion,
  "com.twitter" %% "util-slf4j-api" % versions.twLibVersion,
  "javax.inject" % "javax.inject" % "1",
  "joda-time" % "joda-time" % versions.jodaTime,
  "com.github.nscala-time" %% "nscala-time" % versions.nscalaTime,
  "net.codingwell" %% "scala-guice" % versions.scalaGuice,
  "org.joda" % "joda-convert" % versions.jodaConvert,
  "org.scala-lang" % "scalap" % scalaVersion.value,
  "com.google.inject" % "guice" % versions.guice % Test,
  "com.google.inject.extensions" % "guice-testlib" % versions.guice % Test,
  "com.twitter" %% "finagle-stats" % versions.twLibVersion % Test,
  "org.slf4j" % "slf4j-simple" % versions.slf4j % "test-internal",
  "com.twitter" %% "finatra-http-server" % "22.3.0" % "test" classifier "tests",
  "org.scalatest" %% "scalatest" % versions.scalaTest % "test"
)
