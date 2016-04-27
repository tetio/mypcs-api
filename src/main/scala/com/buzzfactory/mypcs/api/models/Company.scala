package com.buzzfactory.mypcs.api.models

import com.buzzfactory.mypcs.api.models.DateTimeJsonProtocol.DateTimeJsonFormat
import org.joda.time.DateTime
import sprest.models.{Model, ModelCompanion}

/**
  * Created by tetio on 26/04/16.
  */

//case class PrimaryContact(
//  title: String,
//  firstName: String,
//  middleName: String,
//  lastName: String,
//  mobile: String,
//  phoneHome: String,
//  email: String
//)
case class Company(
                    code: String,
                    name: String,

                    lastModification: DateTime,
                    web: String,
                    email: String,
                    //      addressTitle: String,
                    address: String,
                    city: String,
                    region: String,
                    postalCode: String,
                    country: String,
                    phone: String,
                    fax: String,
                    situation: String,
//                    primaryContact: PrimaryContact,
                    //      services: Seq[String],
                    var id: Option[String] = None) extends Model[String]

object Company extends ModelCompanion[Company, String] {
  implicit val companyJsonFormat = jsonFormat14(Company.apply _)
}
