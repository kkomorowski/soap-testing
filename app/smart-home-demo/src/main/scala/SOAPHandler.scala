package dev.hiquality.smarthome

import cask.{Request, Response}
import dev.hiquality.smarthome.SOAPFault

import java.io.{ByteArrayInputStream, File, StringReader}
import java.util
import javax.xml.XMLConstants
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.{Schema, SchemaFactory, Validator}
import scala.util.{Failure, Success, Try}
import scala.xml.{Elem, XML}
import scala.xml.Utility.trim

object SOAPHandler:

  private def faultResponse(body: Elem) =
    Response(trim(body).toString(), 500, headers = Seq("Content-Type" -> "application/xml"))

  private val schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
  private def loadSchema(name: String): Try[StreamSource] =
    for
      xml <- Try(scala.io.Source.fromResource(name).getLines().mkString("\n"))
    yield new StreamSource(new StringReader(xml))

  private val validator =
    val v =
      for
        source    <- loadSchema("smart_home_demo.xsd")
        schema    <- Try(schemaFactory.newSchema(source))
        validator <- Try(schema.newValidator())
      yield validator
    v.get

  def validateRequest(body: String): Either[SOAPFault, String] =
    val result =
      for
        b         <- Try(XML.loadString(body))
        request   <- Try((b \\ "Envelope" \ "Body" \ "_").toString)
        source    <- Try(new StreamSource(new StringReader(request)))
        outcome   <- Try(validator.validate(source))
      yield body

    result match
      case Failure(exception) => Left(SOAPFault("Sender", s"XSD Schema validation failed.\n${exception.toString}"))
      case Success(value) => Right(value)

  def handle(request: Request): Response[String] =
    val body =
      for
        validated <- validateRequest(request.toString)
      yield validated

    body match
      case Left(fault: SOAPFault) => faultResponse(fault.toXML)
      case _ => Response("Your SOAP Request is handled!", 201)