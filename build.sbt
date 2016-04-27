name := "mypcs-api"

organization := "com.buzzfactory"

version := "0.1"

scalaVersion := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io/",
  "Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/",
  "sprest snapshots" at "http://markschaake.github.com/releases"
)

libraryDependencies ++= {
  val akkaV = "2.2.3"
  val sprayV = "1.2.0"
  Seq(
    "io.spray"            %   "spray-can"     % sprayV,
    "io.spray"            %   "spray-routing" % sprayV,
    "io.spray"            %   "spray-testkit" % sprayV,
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV,
    "org.reactivemongo" %% "reactivemongo" % "0.10.0",
    "io.spray" %%  "spray-json" % "1.2.5",
    "sprest" %% "sprest-reactivemongo" % "0.3.0",
    "com.pauldijou" %% "jwt-core" % "0.7.1",
    "joda-time" % "joda-time" % "2.9.3",
    "org.scalatest" %% "scalatest" % "1.9.2" % "test",
    "org.mockito" % "mockito-all" % "1.9.5" % "test"
  )
}

initialCommands := "import com.buzzfactory.mypcs.api._"

//seq(Revolver.settings: _*)

