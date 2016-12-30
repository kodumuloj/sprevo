package controllers

import javax.inject._

import play.api.mvc._

class Application @Inject() extends Controller {

  def index = Action {
    Ok(views.html.index("it works"))
  }

}
