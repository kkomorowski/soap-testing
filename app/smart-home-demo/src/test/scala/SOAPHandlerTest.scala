package dev.hiquality.smarthome

import cask.model.Request
import cask.router.Result.Success
import org.scalatest.flatspec.*
import org.scalatest.matchers.*
import org.slf4j.LoggerFactory

import java.io.StringReader
import javax.xml.XMLConstants
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory
import scala.Right
import scala.io.Source
import scala.util.Try
import scala.xml.XML

class SOAPHandlerTest extends AnyFlatSpec with must.Matchers:

  private val logger = LoggerFactory.getLogger(getClass)

  private val request =
    """<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
      |                  xmlns:web="https://soap.hiquality.dev/smarthome">
      |    <soapenv:Header/>
      |    <soapenv:Body>
      |        <web:GetDevicesRequest/>
      |    </soapenv:Body>
      |</soapenv:Envelope>""".stripMargin

  "SOAPHandler" must "validate request" in:
    SOAPHandler.validateRequest(request) mustBe Right(request)
