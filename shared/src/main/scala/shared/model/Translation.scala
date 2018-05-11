package shared.model

import scala.xml.{Node, NodeSeq}
import upickle.default.{ReadWriter => RW, macroRW}

/**
  * Created by rendong on 16/11/13.
  */
case class Translation(lang: String, content: List[String])

object Translation {
  implicit def rw: RW[Translation] = macroRW

  def fromNode(node: Node): Translation = {
    node.label match {
      case "trd" => Translation(node \@ "lng", List(node.text))
      case "trdgrp" => Translation(node \@ "lng" , node \ "trd" map (_.text) toList)
    }
  }

  def fromNodeSeq(nodeSeq: NodeSeq): Map[String, Translation] = {
    if (nodeSeq.isEmpty)
      Map.empty
    else
      nodeSeq
        .map(Translation.fromNode)
        .map(t => (t.lang, t))
        .toMap
  }
}
