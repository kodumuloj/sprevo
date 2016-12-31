package controllers

import javax.inject._

import play.api.mvc._

class Application @Inject() (webJarAssets: WebJarAssets) extends Controller {

  def index = Action {
    Ok(views.html.index(webJarAssets))
  }

}
