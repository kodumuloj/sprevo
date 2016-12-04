package com.sadhen.sprevo.model

import com.sadhen.sprevo.utils.StringUtils

import scala.xml.Node

/**
  * Created by rendong on 16/12/4.
  */
case class Footnote(content: Option[String], author: Option[String], work: Option[String], location: Option[String])


object Footnote {
  def fromNode(node: Node): Footnote = {
    val ct = node.child.collect {
      case node if node.label == "#PCDATA" && node.text.exists(x => x.isLetter) => node.text
    }
    val content = if (ct.isEmpty) Option.empty else Option(ct.mkString)

    val aut = node \\ "aut"
    val author = if (aut.isEmpty) Option.empty else Option(StringUtils.url2href(aut(0).child.mkString))

    val vrk = node \\ "vrk"
    val work = if (vrk.isEmpty) Option.empty else Option(StringUtils.url2href(vrk(0).child.mkString))

    val lok = node \\ "lok"
    val location = if (lok.isEmpty) Option.empty else Option(StringUtils.url2href(lok(0).child.mkString))

    Footnote(content, author, work, location)
  }
}
