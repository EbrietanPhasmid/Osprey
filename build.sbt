ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "3.3.3"

lazy val root = (project in file("."))
   .settings(
     name := "Osprey",
     idePackagePrefix := Some("com.osprey.app"),
     libraryDependencies += "com.lihaoyi" %% "os-lib" % "0.9.1",
  )
//libraryDependencies ++= Seq(
//  "com.typesafe.slick" %% "slick" % "3.0.0",
//  "org.slf4j" % "slf4j-nop" % "1.6.4"
//)