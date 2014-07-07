package com.eweise.microservice

import akka.actor.{Actor, ActorLogging}
import akka.contrib.pattern.{DistributedPubSubMediator, DistributedPubSubExtension}
import akka.contrib.pattern.DistributedPubSubMediator.{Subscribe, SubscribeAck, Put}

case object PerformWork

case object OK

/**
 * This actor represents some service running in a cluster.
 */
class MicroserviceActor extends Actor with ActorLogging {
  import DistributedPubSubMediator.{Subscribe, SubscribeAck}
  val mediator = DistributedPubSubExtension(context.system).mediator

  mediator ! Put(self)

  def receive = {
    case SubscribeAck(Subscribe("microservice", None, `self`)) ⇒
      println("subscribed")
    case PerformWork =>
      log.info("Miscroservice is performing some work")
      sender() ! OK
    case m@_ => log.warning(s"Microservice received unknown message $m ")
  }
}
