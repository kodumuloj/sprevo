package com.sadhen.sprevo.route

import com.sadhen.sprevo.model.Vortaro
import com.sadhen.sprevo.utils.{HttpUtils, StringUtils}
import org.http4s.rho._
import org.http4s.rho.swagger._

class RevoRoutes extends RhoService {

  "get json by xml file name" **
  GET / "index" +? param[String]("name") |>> { (name: String) =>
    val xmlTry = HttpUtils.getXmlByIndex(StringUtils.word2index(name))
    Ok(Vortaro.fromXml(xmlTry.get).toString)
  }
}
