package com.sadhen.sprevo.model

import com.sadhen.sprevo.utils.StringUtils

import scala.xml.Node

/**
  * Created by rendong on 16/12/4.
  */
case class Definition(content: String, examples: Option[List[Example]])

object Definition {
  def fromNode(node: Node): Definition = {
    val content = node.child.collect {
      case node if node.label == "#PCDATA" && node.text.exists(x => x.isLetter) => node.text
      case node if node.label == "ref" => node.child.mkString
      case node if node.label == "tld" => node.toString
    }.mkString
    val ekzs = node \\ "ekz" map (Example.fromNode) toList
    val examples = if (ekzs.isEmpty) Option.empty else Option(ekzs)
    Definition(StringUtils.removeBlankAndSpace(content), examples)
  }
}
