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
    val search = {event: Event =>
      if (input.value != "") {
        searchByIndex(data, input.value)
      }
    }
    <div>
      <div>{ input } <button onclick={ search }> Search </button></div>
      <h2> { data.bind.index } </h2>
    </div>
  }

  def main(): Unit = {
    dom.render(document.body, render)
  }
}
