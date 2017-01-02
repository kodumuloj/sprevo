package revo

import com.thoughtworks.binding.Binding.Var
import com.thoughtworks.binding.dom
import org.scalajs.dom.document
import org.scalajs.dom.ext.Ajax
import org.scalajs.dom.raw.Event
import org.scalajs.dom.html.Input
import shared.model._

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js
import upickle.default._
import org.scalajs.jquery.jQuery

import scala.language.implicitConversions

object Sprevo extends js.JSApp {


  /**
    * Ajax Request to server, updates data state with number
    * of requests to count.
    * @param data
    */
  def searchByIndex(data: Var[Vortaro], index: String) = {
    val url = s"http://localhost:8080/index/$index"
    Ajax.get(url).onSuccess { case xhr =>
      data := read[Vortaro](xhr.responseText)
      println(data.get.version)
      println(data.get.index)
    }
  }


  @dom
  def render = {
    val data = Var(Vortaro.empty)
    val input: Input = <input class="prompt" type="text" placeholder="" />
    val search = {event: Event =>
      if (input.value != "") {
        println(input.value)
        searchByIndex(data, input.value)
      }
    }
    <div>
      <div class="ui menu">
        <div class="ui category search item">
          <div class="ui transparent icon input">
            { input }
            <i class="search link icon" onclick={ search }></i>
          </div>
          <div class="results"></div>
        </div>
      </div>
      <div class="ui segment">
        { Content(data.bind.index).showVortaro(data).bind }
      </div>
    </div>
  }

  def main(): Unit = {
    dom.render(document.body, render)
    import SemanticUI._
    jQuery(".ui.dropdown").dropdown(js.Dynamic.literal(on = "hover"))
  }
}
