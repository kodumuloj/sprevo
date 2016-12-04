package com.sadhen.sprevo.utils

import com.sadhen.sprevo.model.Vortaro
import org.json4s._
import org.json4s.native.JsonMethods._
import org.scalatest.{FlatSpec, Matchers}



/**
  * Created by rendong on 16/11/13.
  */
class XmlTest extends FlatSpec with Matchers {
  it should "work" in {
    val xmlTry = HttpUtils.getXmlByIndex(StringUtils.word2index("salut"))
    xmlTry.recover { case e =>
      e.printStackTrace()
    }
    xmlTry.foreach { xml =>
      println(pretty(render(parse(Vortaro.fromXml(xml).toString))))
    }
  }
}
