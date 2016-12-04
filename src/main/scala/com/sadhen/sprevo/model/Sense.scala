package com.sadhen.sprevo.model

import scala.xml.Node

/**
  * Created by rendong on 16/11/13.
  */
case class Sense(definition: Option[Definition])

object Sense {
  def fromNode(node: Node): Sense = {
    val definition = node \\ "dif" find(_ => true) map Definition.fromNode
    Sense(definition)
  }
}
