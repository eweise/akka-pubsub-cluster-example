package com.eweise.api

import akka.actor.Actor
import akka.contrib.pattern.DistributedPubSubExtension
import akka.contrib.pattern.DistributedPubSubMediator.Send
import akka.pattern._
import akka.util.Timeout
import com.eweise.microservice.PerformWork
import spray.routing.HttpService
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.util.{Failure, Success}

/**
 * Defines the HTTP endpoints
 */

class ApiRouteActor extends Actor with HttpService {

  val actorRefFactory = context
  implicit val timeout = Timeout(10 seconds)
  val mediator = DistributedPubSubExtension(context.system).mediator
  implicit val ec = ExecutionContext.Implicits.global

  def receive = runRoute {
    path("foo") {
      complete("bar")
    } ~
    path("ping") {
      onComplete(mediator ? Send("/user/microservice", PerformWork, false)) {
        case Success(value) => complete("OK")
        case Failure(e) => complete(e.getMessage)
      }
    }
  }
}
