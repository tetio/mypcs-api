package com.buzzfactory.mypcs.api


import akka.actor.Actor
import com.buzzfactory.mypcs.api.Mongo.{Companies, Persons}
import com.buzzfactory.mypcs.api.models.Person.personJsonFormat
import com.buzzfactory.mypcs.api.models.{Company, Person}
import spray.http.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import spray.httpx.SprayJsonSupport.{sprayJsonMarshaller, sprayJsonUnmarshaller}
import spray.json.pimpAny
import spray.routing.HttpService

import scala.concurrent.ExecutionContext.Implicits.global

class MyServiceActor extends Actor with MyService {
  def actorRefFactory = context

  def receive = runRoute(myRoute)
}

trait MyService extends HttpService {

  lazy val myRoute =
    path("person") {
      put {
        putRoute
      } ~
        get {
          getRoute
        } ~
        delete {
          deleteRoute
        }
    } ~
      path("company") {
        get {
          getCompanyRoute
        } ~
          post {
            postCompanyRoute
          } ~
          put {
            putCompanyRoute
          }
      }

  protected lazy val putRoute =
    entity(as[Person]) { person ⇒
      detach() {
        complete {
          Persons.add(person)
        }
      }
    }

  protected lazy val getCompanyRoute =
    detach() {
      complete {
        Companies.findAll()
      }
    }
  protected lazy val postCompanyRoute =
    entity(as[Company]) { company ⇒
      detach() {
        complete {
          Companies.add(company)
        }
      }
    }

  protected lazy val putCompanyRoute =
    entity(as[Company]) { company ⇒
      detach() {
        complete {
          Companies.update(company)
        }
      }
    }

  protected lazy val getRoute =
    parameters('id.as[String]) { id ⇒
      detach() {
        complete {
          val person = Persons.findById(id)
          person map { person ⇒
            person match {
              case Some(person) ⇒
                HttpResponse(
                  StatusCodes.OK,
                  HttpEntity(ContentTypes.`application/json`, person.toJson.prettyPrint)
                )
              case None ⇒
                HttpResponse(StatusCodes.BadRequest)
            }
          }
        }
      }
    } ~
      parameters('name.as[String]) { name ⇒
        detach() {
          complete {
            Persons.findByName(name)
          }
        }
      } ~
      parameters('age.as[Int]) { age ⇒
        detach() {
          complete {
            Persons.findByAge(age)
          }
        }
      } ~
      detach() {
        complete {
          Persons.findAll()
        }
      }

  protected lazy val deleteRoute =
    detach() {
      dynamic {
        Persons.removeAll()
        complete(StatusCodes.OK)
      }
    }
}
