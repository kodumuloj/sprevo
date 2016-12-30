package shared.utils

/**
  * Created by rendong on 16/11/11.
  */
object StringUtils {
  def word2index(word: String): String = {
    val w0 =
      if (List("o", "a", "i").exists(x => word.endsWith(x)))
        word.substring(0, word.length-1)
      else
        word
    val w1 = w0.replace("ĉ", "cx")
      .replace("Ĉ", "Cx")
      .replace("ĝ", "gx")
      .replace("Ĝ", "Gx")
      .replace("ĥ", "hx")
      .replace("Ĥ", "Hx")
      .replace("ĵ", "jx")
      .replace("Ĵ", "Jx")
      .replace("ŝ", "sx")
      .replace("Ŝ", "Sx")
      .replace("ǔ", "ux")
      .replace("Ǔ", "Ux")
    w1
  }

  def url2href(line: String): String = {
    line.replace("<url ref", "<a href")
      .replace("</url>", "</a>")
  }

  def removeBlankAndSpace(lines: String): String = {
    def removeBlank(line: String): String = {
      if (line.contains("  "))
        removeBlank(line.replace("  ", " "))
      else
        line
    }
    removeBlank(lines.replace("\n", " "))
  }
}
