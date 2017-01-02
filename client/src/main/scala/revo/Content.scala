package revo

import com.thoughtworks.binding.Binding.{Var, Vars}
import com.thoughtworks.binding.dom
import shared.model._
import shared.utils.StringUtils

/**
  * Created by rendong on 17/1/2.
  */
case class Content(index: String) {
  @dom
  def showExample(example: Example) = {
    <div class="item">
      <i class="comment icon"></i>
      { StringUtils.showIndex(example.sentence, index) }
    </div>
  }

  @dom
  def showDefinition(definition: Option[Definition]) = {
    if (definition.isDefined)
      <div class="content">
        <div class="header">
          { StringUtils.showIndex(definition.get.content, index) }
        </div>
        <div class="description">
          { Vars(definition.get.examples: _*).map(example => showExample(example).bind) }
        </div>
      </div>
    else
      <!-- no definition -->
  }

  @dom
  def showTranslation(trd: Translation) = {
    <i class={ Utils.ISO639toISO3166(trd.lang) + " flag" }></i>
    <div class="ui mini horizontal divided list">
    {
      Vars(trd.content: _*) map { item =>
        <div class="item">
          {item}
        </div>
      }
    }
    </div>
  }

  @dom
  def showTranslations(trds: Map[String, Translation]) = {
    {
      Vars(trds.values.toList: _*) map { trd =>
        <div class="item">
          <i class="translate icon"></i>
          { showTranslation(trd).bind }
        </div>
      }
    }
  }

  @dom
  def showSenses(senses: List[Sense]) = {
    <div class="ui list">
    {
      Vars(senses: _*).map { sense =>
        <div class="item">
          <i class="info icon"></i>
          { showDefinition(sense.definition).bind }
          <div style="margin-left: 20px">{ showTranslations(sense.dict).bind }</div>
        </div>
      }
    }
    </div>
  }

  @dom
  def showDerive(derive: Derive) = {
    <div class="card">
      <div class="content">
        <div class="header">
          { derive.name }
        </div>
        <div class="meta">vortaro</div>
        <div class="description">
          { showSenses(derive.sense).bind }
          { showTranslations(derive.dict).bind }
        </div>
      </div>
    </div>
  }

  @dom
  def showVortaro(data: Var[Vortaro]) = {
    <div class="ui one cards">
      { Vars(data.bind.derives: _*).map(derive => showDerive(derive).bind) }
    </div>
  }

}
