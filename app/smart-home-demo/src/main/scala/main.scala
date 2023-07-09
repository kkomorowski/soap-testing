package dev.hiquality.smarthome

import cask._

object main extends cask.MainRoutes:

  private val serviceEndpoint = "smart-home-service"
  @cask.get(serviceEndpoint)
  private def wsdl(wsdl: Option[String] = None) =
    wsdl match
      case Some("") => Response("WSDL for you!!!")
      case _        => Response("Unsupported Operation", 405)

  @cask.post(serviceEndpoint)
  private def soap(request: Request) =
    Response("", 201)

  initialize()
