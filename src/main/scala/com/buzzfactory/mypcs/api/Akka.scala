package com.buzzfactory.mypcs.api

import akka.actor.ActorSystem

object Akka {
  implicit val actorSystem = ActorSystem("actorsystem")
}
