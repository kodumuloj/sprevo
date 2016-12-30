package shared.model

import java.time.format.DateTimeFormatter
import java.time.{LocalDateTime, ZoneOffset}

import scala.language.postfixOps
import scala.xml.Elem


/**
  * Created by rendong on 16/11/12.
  */
case class Vortaro(index: String,
                   version: String,
                   date: Long,
                   derives: List[Derive]) {
}

object Vortaro {
  def parseComment(xml: Elem): (String, String, LocalDateTime) = {
    val comment = xml \ "art" \@ "mrk"
    val parts = comment.split(" ")
    val index = parts(1).split("\\.")(0)
    val version = parts(2)
    val date = parts(3) + " " + parts(4)
    (index, version, LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
  }

  def fromXml(xml: Elem): Vortaro = {
    val comment = parseComment(xml)
    val index = comment._1
    val version = comment._2
    val date = comment._3
    val derives = xml \\ "drv" map (drv => Derive.fromNode(drv)) toList

    Vortaro(index,
      version,
      date.toEpochSecond(ZoneOffset.UTC),
      derives)
  }
}
