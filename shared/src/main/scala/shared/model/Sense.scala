package shared.model

import scala.xml.Node

/**
  * Created by rendong on 16/11/13.
  */
case class Sense(definition: Option[Definition], dict: Map[String, Translation])

object Sense {
  def fromNode(node: Node): Sense = {
    val definition = node \ "dif" find(_ => true) map Definition.fromNode

    val trds = node \ "trd"
    val trdgrps = node \ "trdgrp"
    val dict = Translation.fromNodeSeq(trds ++ trdgrps)
    Sense(definition, dict.getOrElse(Map.empty))
  }
}
