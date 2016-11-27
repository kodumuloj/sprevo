package com.sadhen.sprevo.model

import scala.xml.Node
import org.log4s._

/**
  * Created by rendong on 16/11/13.
  */
case class Derive(dict: Map[String, Translation])

object Derive {
  private val logger = getLogger

  def fromNode(node: Node): Derive = {
    val trds = node \ "trd"
    val trdgrps = node \ "trdgrp"
    val dict = Translation.fromNodeSeq(trds ++ trdgrps)
    Derive(dict)
  }
}
