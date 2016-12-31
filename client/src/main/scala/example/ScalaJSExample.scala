package example

import com.thoughtworks.binding.Binding.Var
import com.thoughtworks.binding.dom
import org.scalajs.dom.document
import org.scalajs.dom.ext.Ajax
import org.scalajs.dom.raw.Event
import org.scalajs.dom.html.Input
import shared.model.Vortaro

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js
import upickle.default._
import org.scalajs.jquery.{JQuery, jQuery}
import scala.language.implicitConversions

object SemanticUI {
  @js.native
  trait SemanticJQuery extends JQuery {
    def accordion(params: js.Any*): SemanticJQuery = js.native
    def dropdown(params: js.Any*): SemanticJQuery = js.native
    def checkbox(): SemanticJQuery = js.native
  }

  implicit def jq2bootstrap(jq: JQuery): SemanticJQuery = jq.asInstanceOf[SemanticJQuery]
}

object ScalaJSExample extends js.JSApp {


  /**
    * Ajax Request to server, updates data state with number
    * of requests to count.
    * @param data
    */
  def searchByIndex(data: Var[Vortaro], index: String) = {
    val url = s"http://localhost:9000/index/$index"
    Ajax.get(url).onSuccess { case xhr =>
      data := read[Vortaro](xhr.responseText)
      println(data.get.version)
      println(data.get.index)
      data.get.derives.foreach(println)
    }
  }

  @dom
  def render = {
    val data = Var(Vortaro.empty)
    val input: Input = <input type="text" />
    val input2: Input = <input class="prompt" type="text" placeholder="Common passwords..." />
    val search = {event: Event =>
      if (input.value != "") {
        searchByIndex(data, input.value)
      }
    }
    <div>
      <select class="ui search dropdown">
        <option>State</option>
        <option value="AL">Alabama</option>
        <option value="AK">Alaska</option>
        <option value="AZ">Arizona</option>
        <option value="AR">Arkansas</option>
        <option value="CA">California</option>
        <option value="CO">Colorado</option>
        <option value="CT">Connecticut</option>
        <option value="DE">Delaware</option>
        <option value="DC">District Of Columbia</option>
        <option value="FL">Florida</option>
      </select>
      <button class="ui primary button">
        Save
      </button>
      <button class="ui button">
        Discard
      </button>
      <div class="ui dropdown">
        <div class="text">File</div>
        <i class="dropdown icon"></i>
        <div class="menu">
          <div class="item">New</div>
        </div>
      </div>
      <div>{ input } <button onclick={ search } style="background"> Search </button></div>
      <h2> { data.bind.index } </h2>
    </div>
  }

  def main(): Unit = {
    dom.render(document.body, render)
    import SemanticUI._
    jQuery(".ui.dropdown").dropdown(js.Dynamic.literal(on = "hover"))
  }
}
