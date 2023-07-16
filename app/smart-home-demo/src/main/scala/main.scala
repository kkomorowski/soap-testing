package dev.hiquality.smarthome

import cask.*
import org.slf4j.LoggerFactory

object main extends cask.MainRoutes:

  private val logger = LoggerFactory.getLogger(getClass)
  override def verbose: Boolean = true

  private val serviceEndpoint = "smart-home-service"
  @cask.get(serviceEndpoint)
  private def wsdl(wsdl: Option[String] = None) =
    wsdl match
      case Some("") => WSDLHandler.handle
      case _        => Response("Unsupported operation", 405)

  @cask.post(serviceEndpoint)
  private def soap(request: Request) = {
    logger.debug(request.toString)
    val response = SOAPHandler.handle(request)
    logger.debug(response.toString)
    response
  }

  initialize()
