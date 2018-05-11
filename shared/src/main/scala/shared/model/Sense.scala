package shared.model

import scala.xml.Node
import upickle.default.{ReadWriter => RW, macroRW}

/**
  * Created by rendong on 16/11/13.
  */
case class Sense(definition: Option[Definition], dict: Map[String, Translation])

object Sense {
  implicit def rw: RW[Sense] = macroRW

  def fromNode(node: Node): Sense = {
    val definition = node \ "dif" find(_ => true) map Definition.fromNode

    val trds = node \ "trd"
    val trdgrps = node \ "trdgrp"
    val dict = Translation.fromNodeSeq(trds ++ trdgrps)
    Sense(definition, dict)
  }
}
