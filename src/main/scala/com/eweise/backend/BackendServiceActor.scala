package com.eweise.backend

import akka.actor.{Actor, ActorLogging}
import akka.contrib.pattern.DistributedPubSubExtension
import akka.contrib.pattern.DistributedPubSubMediator.Put

@SerialVersionUID(1L)
sealed trait Message

case object PerformWork extends Message

case object OK extends Message

/**
 * This actor represents some service running in a cluster.
 */
class BackendServiceActor extends Actor with ActorLogging {

  import akka.contrib.pattern.DistributedPubSubMediator.{Subscribe, SubscribeAck}
  import context._

  val mediator = DistributedPubSubExtension(context.system).mediator

  mediator ! Put(self)

  def receive = {
    case PerformWork =>
      log.info("Backend Service is performing some work")
      sender() ! OK
  }

}
