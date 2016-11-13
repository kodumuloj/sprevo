package com.sadhen.sprevo.utils

import com.sadhen.sprevo.model.Vortaro
import org.junit.Test
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.native.JsonMethods._



/**
  * Created by rendong on 16/11/13.
  */
class XmlTest {
  @Test
  def testXml: Unit = {
    val xmlTry = HttpUtils.getXmlByIndex(StringUtils.word2index("tamen"))
    xmlTry.recover { case e =>
      e.printStackTrace()
    }
    xmlTry.foreach { xml =>
      println(pretty(render(parse(Vortaro.fromXml(xml).toString))))
    }
  }
}
