package shared.model

import shared.utils.StringUtils

import scala.xml.Node
import upickle.default.{ReadWriter => RW, macroRW}

/**
  * Created by rendong on 16/12/4.
  */
case class Footnote(content: Option[String], author: Option[String], work: Option[String], location: Option[String])


object Footnote {
  implicit def rw: RW[Footnote] = macroRW

  def fromNode(node: Node): Footnote = {
    val ct = node.child.collect {
      case elem if elem.label == "#PCDATA" && elem.text.exists(x => x.isLetter) => elem.text
    }
    val content = if (ct.isEmpty) Option.empty else Option(ct.mkString)

    val aut = node \ "aut"
    val author = if (aut.isEmpty) Option.empty else Option(StringUtils.url2href(aut.head.child.mkString))

    val vrk = node \ "vrk"
    val work = if (vrk.isEmpty) Option.empty else Option(StringUtils.url2href(vrk.head.child.mkString))

    val lok = node \ "lok"
    val location = if (lok.isEmpty) Option.empty else Option(StringUtils.url2href(lok.head.child.mkString))

    Footnote(content, author, work, location)
  }
}
