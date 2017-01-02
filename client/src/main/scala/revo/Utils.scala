package revo

/**
  * Created by rendong on 17/1/2.
  */
object Utils {
  def ISO639toISO3166(code: String): String = code match {
    case "zh" => "cn"
    case "en" => "gb"
    case default => default
  }
}
