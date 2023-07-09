package dev.hiquality.smarthome

import cask._

object main extends cask.MainRoutes:

  private val serviceEndpoint = "smart-home-service"
  @cask.get(serviceEndpoint)
  private def wsdl(wsdl: Option[String] = None) =
    wsdl match
      case Some("") => WSDLHandler.handle
      case _        => Response("Unsupported operation", 405)

  @cask.post(serviceEndpoint)
  private def soap(request: Request) =
    Response("", 201)

  initialize()
