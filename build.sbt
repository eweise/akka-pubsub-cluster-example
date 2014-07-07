name := "akka-pubsub-cluster-example"

version := "0.1"

scalaVersion := "2.11.1"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += "Spray.io" at "http://repo.spray.io"

libraryDependencies ++= {
  val akkaV = "2.3.3"
  val sprayV = "1.3.1-20140423"
  Seq(
    "io.spray" %% "spray-can" % sprayV,
    "io.spray" %% "spray-routing" % sprayV,
    "io.spray" %% "spray-json" % "1.2.6",
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-cluster" % akkaV,
    "com.typesafe.akka" %% "akka-contrib" % akkaV
  )
}


