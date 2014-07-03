package com.eweise.microservice

import akka.actor.{Props, ActorSystem}
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import scala.concurrent.duration
import duration._

/**
 * Pass in the port as first argument so that we can run many microservices
 */
object MicroserviceBoot extends App {

  implicit val timeout = Timeout(10 seconds)

  val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.port=${args(0)}").withFallback(ConfigFactory.load)

  implicit val actorSystem = ActorSystem("cluster-example", config)

  actorSystem.actorOf(Props[MicroserviceActor], "microservice")

}
