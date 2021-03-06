package com.eweise.api

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import spray.can.Http
import scala.concurrent.duration
import duration._
object ApiBoot extends App {

  implicit val timeout = Timeout(10 seconds)

  val config = ConfigFactory.parseString("akka.remote.netty.tcp.port=2551").withFallback(ConfigFactory.load())

  /**
   * All actors that want to belong to the same cluster need to use the same
   * ActorSystem name
   */
  implicit val actorSystem = ActorSystem("cluster-example", config)

  val apiRoutes = actorSystem.actorOf(Props[WebServiceActor], "api-routes")

  IO(Http) ! Http.Bind(apiRoutes, interface = "localhost", port=8085)
}
