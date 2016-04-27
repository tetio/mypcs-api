package com.buzzfactory.mypcs.api.models

import sprest.models.{Model, ModelCompanion}

case class Person(
  name: String,
  age: Int,
  var id: Option[String] = None) extends Model[String]

object Person extends ModelCompanion[Person, String] {
  implicit val personJsonFormat = jsonFormat3(Person.apply _)
}
