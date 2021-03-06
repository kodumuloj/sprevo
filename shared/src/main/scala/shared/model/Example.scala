package shared.model

import scala.xml.Node
import shared.utils.StringUtils
import upickle.default.{ReadWriter => RW, macroRW}

/**
  * Created by rendong on 16/12/4.
  */
case class Example(sentence: String, footnote: Option[Footnote])

object Example {
  implicit def rw: RW[Example] = macroRW

  def fromNode(node: Node): Example = {
    val sentence = node.child.collect {
      case elem if elem.label == "#PCDATA" && elem.text.exists(x => x.isLetter) => elem.text
      case elem if elem.label == "tld" => elem.toString
    }.mkString
    val footnote = (node \ "fnt").find(_ => true).map(Footnote.fromNode)
    Example(StringUtils.removeBlankAndSpace(sentence), footnote)
  }
}
