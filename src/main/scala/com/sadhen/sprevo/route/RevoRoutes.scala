package com.sadhen.sprevo.route

import com.sadhen.sprevo.model.Vortaro
import com.sadhen.sprevo.utils.{HttpUtils, MongoDB, StringUtils}
import org.http4s.rho._
import org.http4s.rho.swagger._

class RevoRoutes extends RhoService {

  "get json by xml file name" **
  GET / "index" +? param[String]("name") |>> { (name: String) =>
    val od = MongoDB.getDocumentByIndex(name)
    if (od.isDefined) {
      Ok(od.get)
    } else {
      val xmlTry = HttpUtils.getXmlByIndex(StringUtils.word2index(name))
      if (xmlTry.isSuccess) {
        val json = Vortaro.fromXml(xmlTry.get).toString
        MongoDB.insertOne(json)
        Ok(json)
      } else {
        Ok("not found")
      }
    }
  }
}
