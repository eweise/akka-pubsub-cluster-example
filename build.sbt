name := "akka-pubsub-cluster-example"

version := "0.1"

scalaVersion := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += "Spray.io" at "http://repo.spray.io"

libraryDependencies ++= {
  val akkaV = "2.3.3"
  val sprayV = "1.3.3"
  Seq(
    "io.spray" %% "spray-can" % sprayV,
    "io.spray" %% "spray-routing" % sprayV,
    "io.spray" %% "spray-json" % "1.3.2",
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-cluster" % akkaV,
    "com.typesafe.akka" %% "akka-contrib" % akkaV
  )
}


