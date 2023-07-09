package dev.hiquality.smarthome

import cask.Response

import scala.io.{BufferedSource, Source}

object WSDLHandler:

  private lazy val wsdl = Source.fromResource("smart_home_demo.wsdl").getLines().mkString("\n")
  def handle: Response[String] = Response(wsdl, headers = Seq("Content-Type" -> "application/xml"))

