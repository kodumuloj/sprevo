package com.sadhen.sprevo.model

import com.sadhen.sprevo.utils.StringUtils

import scala.xml.Node

/**
  * Created by rendong on 16/12/4.
  */
case class Example(sentence: String, footnote: Option[Footnote])

object Example {
  def fromNode(node: Node): Example = {
    val sentence = node.child.collect {
      case node if node.label == "#PCDATA" && node.text.exists(x => x.isLetter) => node.text
      case node if node.label == "tld" => node.toString
    }.mkString
    val footnote = (node \\ "fnt").find(_ => true).map(Footnote.fromNode)
    Example(StringUtils.removeBlankAndSpace(sentence), footnote)
  }
}