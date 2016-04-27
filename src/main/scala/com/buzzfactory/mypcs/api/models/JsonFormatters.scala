package com.buzzfactory.mypcs.api.models

import org.joda.time.{DateTime, DateTimeZone}
import org.joda.time.format.ISODateTimeFormat
import reactivemongo.bson.BSONObjectID
import reactivemongo.bson.Subtype.{OldUuidSubtype, UuidSubtype}
import spray.json.deserializationError
import spray.json.{DefaultJsonProtocol, JsString, JsValue, RootJsonFormat}
import sprest.models.UUIDId

/**
  * Created by suin on 26/04/16.
  */
object JsonFormatters extends DefaultJsonProtocol {
  implicit object DateTimeJsonFormat extends RootJsonFormat[DateTime] {
    private lazy val format = ISODateTimeFormat.dateTimeNoMillis()
    def write(datetime: DateTime): JsValue = JsString(format.print(datetime.withZone(DateTimeZone.UTC)))
    def read(json: JsValue): DateTime = json match {
      case JsString(x) => format.parseDateTime(x)
      case x => deserializationError(s"Expected DateTime, but got $x")
    }
  }

//  implicit object ObjectIdJasonFormat extends RootJsonFormat[UUIDId] {
//    private lazy val format = UuidSubtype
//    def write(obj: BSONObjectID): JsValue = JsString(obj.toString)
//    def read(json: JsValue): BSONObjectID = json match {
//      case JsString(str) => BSONObjectID.parse(str)
//      case x => deserializationError(s"Expected BSONObjectID, but got $x")
//    }
//  }
}
