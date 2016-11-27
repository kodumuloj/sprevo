name := "sprevo"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.7"

val rhoVersion = "0.13.1"

val http4sVersion = "0.14.11"

libraryDependencies ++= Seq(
  "org.http4s" %% "rho-swagger" % rhoVersion,
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "org.log4s" %% "log4s" % "1.1.5",
  "org.json4s" %% "json4s-native" % "3.5.0",
  "org.scalatest" %% "scalatest" % "3.0.1"
)
