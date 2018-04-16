import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      name := "teads_contest",
      organization := "tgits",
      scalaVersion := "2.12.5",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "fpis_chap4_option",
    libraryDependencies += scalaTest % Test
  )
