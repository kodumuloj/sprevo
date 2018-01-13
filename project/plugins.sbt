// Comment to get more information during initialization
logLevel := Level.Debug

// Resolvers
resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

resolvers += Resolver.url("heroku-sbt-plugin-releases",
  url("https://dl.bintray.com/heroku/sbt-plugins/"))(Resolver.ivyStylePatterns)

// Sbt plugins
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.11")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.6")

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.21")

addSbtPlugin("com.vmunier" % "sbt-web-scalajs" % "1.0.6")

addSbtPlugin("com.typesafe.sbt" % "sbt-gzip" % "1.0.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.1.3")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.2.4")
