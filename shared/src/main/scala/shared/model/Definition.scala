package shared.model

import scala.xml.Node
import scala.language.postfixOps

import shared.utils.StringUtils

/**
  * Created by rendong on 16/12/4.
  */
case class Definition(content: String, examples: Option[List[Example]])

object Definition {
  def fromNode(node: Node): Definition = {
    val content = node.child.collect {
      case elem if elem.label == "#PCDATA" && elem.text.exists(x => x.isLetter) => elem.text
      case elem if elem.label == "ref" => elem.child.mkString
      case elem if elem.label == "tld" => elem.toString
    }.mkString
    val ekzs = node \ "ekz" map Example.fromNode toList
    val examples = if (ekzs.isEmpty) Option.empty else Option(ekzs)
    Definition(StringUtils.removeBlankAndSpace(content), examples)
  }
}
