package shared.model

import scala.language.postfixOps
import scala.xml.Node

/**
  * Created by rendong on 16/11/13.
  */
case class Derive(name: String, sense: List[Sense], dict: Map[String, Translation])

object Derive {
  def fromNode(node: Node): Derive = {
    val mark = node \@ "mrk"
    val (base, body) = mark.span(_ != '.')
    val name = body.drop(1).replace("0", base)
      .replace("CXcx", "Ĉ")
      .replace("GXgx", "Ĝ")
      .replace("HXhx", "Ĥ")
      .replace("JXjx", "Ĵ")
      .replace("SXsx", "Ŝ")
      .replace("UXux", "Ǔ")
      .replace("cx", "ĉ")
      .replace("gx", "ĝ")
      .replace("hx", "ĥ")
      .replace("jx", "ĵ")
      .replace("sx", "ŝ")
      .replace("ux", "ǔ")

    val snc = node \ "snc"
    val sense =
      if (snc.isEmpty)
        List.empty
      else
        snc map Sense.fromNode toList

    val trds = node \ "trd"
    val trdgrps = node \ "trdgrp"
    val dict = Translation.fromNodeSeq(trds ++ trdgrps)

    Derive(name, sense, dict)
  }
}
