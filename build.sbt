lazy val scalaV = "2.12.6"
lazy val upickleV = "0.6.5"
lazy val jQueryV = "2.1.3"
lazy val semanticV = "2.2.2"

lazy val server = (project in file("server")).settings(
  scalaVersion := scalaV,
  scalaJSProjects := Seq(client),
  pipelineStages in Assets := Seq(scalaJSPipeline),
  pipelineStages := Seq(digest, gzip),
  // triggers scalaJSPipeline when using compile or continuous compilation
  compile in Compile := ((compile in Compile) dependsOn scalaJSPipeline).value,
  libraryDependencies += guice,
  libraryDependencies ++= Seq(
    "com.vmunier" %% "scalajs-scripts" % "1.1.1",
    "org.json4s" %% "json4s-native" % "3.5.0",
    "com.github.tototoshi" %% "play-json4s-native" % "0.8.0",
    //"com.github.tototoshi" %% "play-json4s-test-native" % "0.8.0" % Test,
    "com.lihaoyi" %% "upickle" % upickleV,
    "org.webjars" %% "webjars-play" % "2.6.3",
    "org.webjars" % "Semantic-UI" % semanticV,
    "org.webjars" % "jquery" % jQueryV,
    "org.scalatest" %% "scalatest" % "3.0.3" % Test,
    specs2 % Test
  ),
).enablePlugins(PlayScala).
  dependsOn(sharedJvm)

lazy val client = (project in file("client")).settings(
  scalaVersion := scalaV,
  scalacOptions ++= Seq("-Xmax-classfile-name","78"),
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
  scalaJSUseMainModuleInitializer := true,
  jsDependencies ++= Seq(
    "org.webjars" % "jquery" % jQueryV / "jquery.js" minified "jquery.min.js",
    "org.webjars" % "Semantic-UI" % semanticV / "semantic.js" minified "semantic.min.js" dependsOn "jquery.js"
  ),
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.2",
    "org.scala-lang.modules" %% "scala-xml" % "1.0.6",
    "com.thoughtworks.binding" %%% "dom" % "10.0.2",
    "com.thoughtworks.binding" %%% "futurebinding" % "10.0.2",
    "com.lihaoyi" %%% "upickle" % upickleV,
    "be.doeraene" %%% "scalajs-jquery" % "0.9.1",
    "fr.hmil" %%% "roshttp" % "2.1.0"
  )
).enablePlugins(ScalaJSPlugin, ScalaJSWeb).
  dependsOn(sharedJs)

lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared")).
  settings(
    scalaVersion := scalaV,
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "upickle" % upickleV,
      "org.scala-lang.modules" %% "scala-xml" % "1.0.6"
    )
  ).jsConfigure(_ enablePlugins ScalaJSWeb)

lazy val sharedJvm = shared.jvm
lazy val sharedJs = shared.js
