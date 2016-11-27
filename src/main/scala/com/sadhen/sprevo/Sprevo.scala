package com.sadhen.sprevo

import java.util.concurrent.Executors

import com.sadhen.sprevo.route.RevoRoutes
import org.http4s.HttpService
import org.http4s.rho.swagger.SwaggerSupport
import org.http4s.server.{ServerApp, ServerBuilder}
import org.http4s.server.blaze.BlazeBuilder
import org.log4s.getLogger

import scala.util.Properties.envOrNone

class Sprevo(host: String, port: Int) {
  private val logger = getLogger
  private val pool   = Executors.newCachedThreadPool()

  def rhoRoutes = new RevoRoutes().toService(SwaggerSupport())

  val routes = rhoRoutes

  val service: HttpService = routes.local { req =>
    val path = req.uri.path
    logger.info(s"${req.remoteAddr.getOrElse("null")} -> ${req.method}: $path")
    req
  }

  def build(): ServerBuilder =
    BlazeBuilder
      .bindHttp(port, host)
      .mountService(service)
      .withServiceExecutor(pool)
}

object Sprevo extends ServerApp {
  val ip   = "0.0.0.0"
  val port = envOrNone("HTTP_PORT") map (_.toInt) getOrElse (8080)

  override def server(args: List[String]) =
    new Sprevo(ip, port)
      .build()
      .start
}