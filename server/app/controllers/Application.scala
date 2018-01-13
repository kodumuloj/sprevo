package controllers

import javax.inject._

import play.api.mvc._

import org.webjars.play.WebJarsUtil

class Application @Inject() (webJarsUtil: WebJarsUtil) extends Controller {

  def index = Action {
    Ok(views.html.index(webJarsUtil))
  }

}
