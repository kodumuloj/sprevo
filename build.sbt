lazy val scalaV = "2.11.8"
lazy val upickleV = "0.4.3"

lazy val server = (project in file("server")).settings(
  scalaVersion := scalaV,
  scalaJSProjects := Seq(client),
  pipelineStages in Assets := Seq(scalaJSPipeline),
  pipelineStages := Seq(digest, gzip),
  // triggers scalaJSPipeline when using compile or continuous compilation
  compile in Compile <<= (compile in Compile) dependsOn scalaJSPipeline,
  libraryDependencies ++= Seq(
    "com.h2database" % "h2" % "1.4.192",
    "com.typesafe.play" %% "play-slick" % "2.0.0",
    "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0",
    "com.vmunier" %% "scalajs-scripts" % "1.0.0",
    "org.mongodb.scala" %% "mongo-scala-driver" % "1.2.1",
    "org.json4s" %% "json4s-native" % "3.5.0",
    "com.github.tototoshi" %% "play-json4s-native" % "0.5.0",
    //"com.github.tototoshi" %% "play-json4s-test-native" % "0.5.0" % Test,
    "com.lihaoyi" %% "upickle" % upickleV,
    "org.scalatest" %% "scalatest" % "3.0.1" % Test,
    specs2 % Test
  ),
  // Compile the project before generating Eclipse files, so that generated .scala or .class files for views and routes are present
  EclipseKeys.preTasks := Seq(compile in Compile)
).enablePlugins(PlayScala).
  dependsOn(sharedJvm)

lazy val client = (project in file("client")).settings(
  scalaVersion := scalaV,
  persistLauncher := true,
  scalacOptions ++= Seq("-Xmax-classfile-name","78"),
  persistLauncher in Test := false,
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.1",
    "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.6",
    "com.thoughtworks.binding" %%% "dom" % "10.0.0-M1",
    "com.thoughtworks.binding" %%% "futurebinding" % "10.0.0-M1",
    "com.lihaoyi" %%% "upickle" % upickleV,
    "fr.hmil" %%% "roshttp" % "1.1.0"
  )
).enablePlugins(ScalaJSPlugin, ScalaJSWeb).
  dependsOn(sharedJs)

lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared")).
  settings(
    scalaVersion := scalaV,
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %% "scala-xml" % "1.0.6"
    )
  ).jsConfigure(_ enablePlugins ScalaJSWeb)

lazy val sharedJvm = shared.jvm
lazy val sharedJs = shared.js

// loads the server project at sbt startup
onLoad in Global := (Command.process("project server", _: State)) compose (onLoad in Global).value
