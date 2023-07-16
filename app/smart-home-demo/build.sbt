ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.0"

lazy val root = (project in file("."))
  .settings(
    name := "smart-home-demo",
    idePackagePrefix := Some("dev.hiquality.smarthome")
  )

libraryDependencies += "com.lihaoyi" %% "cask" % "0.9.1"
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.1.0"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.4.8"


// Test dependencies
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.16" % "test"