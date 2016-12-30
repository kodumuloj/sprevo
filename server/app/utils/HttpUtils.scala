package utils

import scala.util.Try
import scala.xml.{Elem, XML}

/**
  * Created by rendong on 16/11/12.
  */
object HttpUtils {
  private val revoUrl = "http://www.reta-vortaro.de"

  def getXmlByIndex(index: String): Try[Elem] = {
    val url = revoUrl + "/revo/xml/" + index + ".xml"
    Try {
      XML.load(url)
    }
  }
}
