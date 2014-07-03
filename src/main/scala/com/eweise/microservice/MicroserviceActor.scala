package com.eweise.microservice

import akka.actor.{Actor, ActorLogging}
import akka.contrib.pattern.DistributedPubSubExtension
import akka.contrib.pattern.DistributedPubSubMediator.Put

case object PerformWork

case object OK

/**
 * This actor represents some service running in a cluster.
 */
class MicroserviceActor extends Actor with ActorLogging {

  val mediator = DistributedPubSubExtension(context.system).mediator

  mediator ! Put(self)

  def receive = {
    case PerformWork =>
      log.info("Miscroservice is performing some work")
      sender() ! OK
    case m@_ => log.warning(s"Microservice received unknown message $m ")
  }
}
