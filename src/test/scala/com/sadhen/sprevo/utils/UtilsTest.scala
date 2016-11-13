import com.sadhen.sprevo.utils.{HttpUtils, StringUtils}
import org.junit.Test

class UtilsTest {
  @Test
  def testUtils = {
    val index = StringUtils.word2index("pomoo")
    println(index)
    val xmlTry = HttpUtils.getXmlByIndex(index)
    println(xmlTry.isSuccess)
    xmlTry.recover { case e =>
      e.printStackTrace()
    }
    xmlTry.foreach(xml => println(xml))
  }
}