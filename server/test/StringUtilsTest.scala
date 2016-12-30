import org.scalatest.{FlatSpec, Matchers}
import shared.utils.StringUtils

/**
  * Created by rendong on 16/12/4.
  */
class StringUtilsTest extends FlatSpec with Matchers {
  it should "work 0" in {
    val url = StringUtils.url2href("a <url ref=\"http://www.liberafolio.org/2005/Agado/zikofinnlando/\">content</url>b")
    assert(url == "a <a href=\"http://www.liberafolio.org/2005/Agado/zikofinnlando/\">content</a>b")
  }
}
