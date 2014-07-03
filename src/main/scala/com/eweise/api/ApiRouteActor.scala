package com.eweise.api

import akka.actor.Actor
import akka.contrib.pattern.DistributedPubSubExtension
import akka.contrib.pattern.DistributedPubSubMediator.Send
import akka.util.Timeout
import com.eweise.microservice.{OK, PerformWork}
import spray.http.StatusCodes.{ServerError, Success}
import spray.routing.HttpService
import scala.concurrent.duration
import duration._
import akka.pattern._

/**
 * Defines the HTTP endpoints
 */

class ApiRouteActor extends Actor with HttpService {

  val actorRefFactory = context
  val timeout = Timeout(10 seconds)
  val mediator = DistributedPubSubExtension(context.system).mediator

  def receive = runRoute {
    path("ping") {
      onComplete(mediator ? Send("/user/microservice", PerformWork, true)) {
        case OK => complete(Success)
        case _ => complete(ServerError(500))
      }
    }
  }
}
