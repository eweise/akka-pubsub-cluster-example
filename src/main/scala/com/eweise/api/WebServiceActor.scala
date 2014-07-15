package com.eweise.api

import akka.actor.Actor
import akka.contrib.pattern.DistributedPubSubExtension
import akka.contrib.pattern.DistributedPubSubMediator.Send
import akka.pattern._
import akka.util.Timeout
import com.eweise.backend.PerformWork
import spray.routing.HttpService
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.util.{Failure, Success}

/**
 * Defines the HTTP endpoints
 */

class WebServiceActor extends Actor with HttpService {
  implicit val timeout = Timeout(10 seconds)

  val actorRefFactory = context
  val mediator = DistributedPubSubExtension(context.system).mediator
  implicit val ec = ExecutionContext.Implicits.global

  def receive = runRoute {
    path("dowork") {
      onComplete(mediator ? Send("/user/backend-service", PerformWork, false)) {
        case Success(value) => complete("OK")
        case Failure(e) => complete(e.getMessage)
      }
    }
  }
}
