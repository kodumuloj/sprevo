package controllers

import javax.inject.Inject

import play.api.mvc._
import shared.model.Vortaro
import shared.utils.StringUtils
import utils.HttpUtils
import upickle.default._

/**
  * Created by rendong on 16/12/30.
  */
class Revo @Inject() extends Controller {

  def index(ind: String) = Action {
    val xml = HttpUtils.getXmlByIndex(StringUtils.word2index(ind))
    if (xml.isSuccess)
      Ok(write(Vortaro.fromXml(xml.get)))
    else
      NoContent
  }
}
