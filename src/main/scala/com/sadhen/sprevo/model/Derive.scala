package com.sadhen.sprevo.model

import scala.xml.Node
import org.log4s._

/**
  * Created by rendong on 16/11/13.
  */
case class Derive(name: String, sense: Option[Sense], dict: Option[Map[String, Translation]])

object Derive {
  private val logger = getLogger

  def fromNode(node: Node): Derive = {
    val mark = node \@ "mrk"
    val (base, body) = mark.span(_ != '.')
    val name = body.drop(1).replace("0", base)

    val snc = node \ "snc"
    val sense = snc find(_ => true) map(Sense.fromNode)

    val trds = node \ "trd"
    val trdgrps = node \ "trdgrp"
    val dict = Translation.fromNodeSeq(trds ++ trdgrps)

    Derive(name, sense, dict)
  }
}
