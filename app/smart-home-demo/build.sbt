ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.0"

lazy val root = (project in file("."))
  .settings(
    name := "smart-home-demo",
    idePackagePrefix := Some("dev.hiquality.smarthome")
  )

libraryDependencies += "com.lihaoyi" %% "cask" % "0.9.1"
