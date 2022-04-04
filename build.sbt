import sbt.plugins.DependencyTreeKeys.dependencyDotFile

scalaVersion := "2.13.8"
//render dot file to `./dependencies.dot`
lazy val versions = new {

  val releaseVersion = "21.2.0"
  // All Twitter library releases are date versioned as YY.MM.patch
  val twLibVersion = releaseVersion
  val guice = "4.2.3"
  val jodaConvert = "1.5"
  val jodaTime = "2.10.10"
  val nscalaTime = "2.22.0"
  val scalaGuice = "4.2.11"
  val scalaTest = "3.1.2"
  val slf4j = "1.7.30"
  val finatra = "21.2.0"
}

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  "Twitter maven" at "https://maven.twttr.com",
  "Finatra Repo" at "https://twitter.github.com/finatra"
)

libraryDependencies ++= Seq(
  "com.twitter" %% "finatra-http" % "21.2.0",
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
  "org.slf4j" % "slf4j-simple" % versions.slf4j % "test-internal"
)

// https://mvnrepository.com/artifact/com.twitter/finatra-http
libraryDependencies += "org.scalatest" %% "scalatest" % versions.scalaTest % "test"
libraryDependencies += "com.twitter" %% "finatra-http" % "21.2.0" % "test"
libraryDependencies += "com.twitter" %% "inject-server" % "21.2.0" % "test"
libraryDependencies += "com.twitter" %% "inject-app" % "21.2.0" % "test"
libraryDependencies += "com.twitter" %% "inject-core" % "21.2.0" % "test"
libraryDependencies += "com.twitter" %% "inject-modules" % "21.2.0" % "test"
libraryDependencies += "com.google.inject.extensions" % "guice-testlib" % "5.1.0" % "test"
libraryDependencies += "com.twitter" %% "finatra-jackson" % "21.2.0" % "test"

libraryDependencies += "com.twitter" %% "finatra-http-server" % "21.3.0" % "test" classifier "tests"
libraryDependencies += "com.twitter" %% "finatra-http" % "21.2.0" % "test" classifier "tests"
libraryDependencies += "com.twitter" %% "inject-server" % "21.2.0" % "test" classifier "tests"
libraryDependencies += "com.twitter" %% "inject-app" % "21.2.0" % "test" classifier "tests"
libraryDependencies += "com.twitter" %% "inject-core" % "21.2.0" % "test" classifier "tests"
libraryDependencies += "com.twitter" %% "inject-modules" % "21.2.0" % "test" classifier "tests"
libraryDependencies += "com.google.inject.extensions" % "guice-testlib" % "5.1.0" % "test" classifier "tests"
libraryDependencies += "com.twitter" %% "finatra-jackson" % "21.2.0" % "test" classifier "tests"

// https://mvnrepository.com/artifact/org.specs2/specs2-core
//libraryDependencies += "org.specs2" %% "specs2-core" % "5.0.0" % Test
