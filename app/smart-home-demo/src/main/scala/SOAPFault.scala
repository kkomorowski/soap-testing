package dev.hiquality.smarthome

import scala.xml.Elem

case class SOAPFault(codeValue: String, reasonText: String):
 def toXML: Elem =
    <env:Envelope xmlns:env="http://www.w3.org/2003/05/soap-envelope">
      <env:Header/>
      <env:Body>
        <env:Fault>
          <env:Code>
            <env:Value>{codeValue}</env:Value>
          </env:Code>
          <env:Reason>
            <env:Text xml:lang="en-US">{reasonText}</env:Text>
          </env:Reason>
        </env:Fault>
      </env:Body>
    </env:Envelope>
