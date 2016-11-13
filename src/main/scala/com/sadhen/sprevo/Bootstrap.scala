package com.sadhen.sprevo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
  * Created by rendong on 16/11/11.
  */
object Bootstrap extends App {
  val app = new SpringApplication(classOf[Bootstrap], "classpath*:/spring/*.xml")
  app.run(args: _*)
}

@SpringBootApplication
class Bootstrap
