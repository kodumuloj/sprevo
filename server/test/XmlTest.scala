import org.scalatest.{FlatSpec, Matchers}
import shared.utils.StringUtils
import utils.HttpUtils
import org.json4s.native.JsonMethods._
import org.json4s.DefaultFormats
import org.json4s.native.Serialization.write
import shared.model.Vortaro

/**
  * Created by rendong on 16/11/13.
  */
class XmlTest extends FlatSpec with Matchers {
  implicit val formats = DefaultFormats

  "it" should "work" in {
    val xmlTry = HttpUtils.getXmlByIndex(StringUtils.word2index("salut"))
    xmlTry.recover { case e =>
      e.printStackTrace()
    }
    xmlTry.foreach { xml =>
      println(pretty(render(parse(write(Vortaro.fromXml(xml))))))
    }
  }

  "ref" should "be handled" in {
    val xmlTry = HttpUtils.getXmlByIndex(StringUtils.word2index("cxin"))
    xmlTry.recover { case e =>
      e.printStackTrace()
    }
    xmlTry.foreach { xml =>
      println(pretty(render(parse(write(Vortaro.fromXml(xml))))))
    }
  }

  "subsnc" should "be handled" in {
    val xmlTry = HttpUtils.getXmlByIndex(StringUtils.word2index("fund"))

    xmlTry.recover { case e =>
      e.printStackTrace()
    }
    xmlTry.foreach { xml =>
      println(pretty(render(parse(write(Vortaro.fromXml(xml))))))
    }
  }
}
