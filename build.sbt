organization := "com.parascal"

name := "scalatra-swagger-api-annotations-test"

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.10" % "2.0.M5b" % "test",
  "org.scalatra" %% "scalatra" % "2.2.2-SNAPSHOT",
  "org.scalatra" %% "scalatra-json" % "2.2.2-SNAPSHOT", 
  "org.scalatra" %% "scalatra-swagger" % "2.2.2-SNAPSHOT", 
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.10.v20130312" % "container;compile",
  "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts
    (Artifact("javax.servlet", "jar", "jar")),
  "com.wordnik" % "swagger-core_2.10.0" % "1.2.0",
  "com.wordnik" % "swagger-annotations_2.10.0" %  "1.2.0",
  "org.json4s" %% "json4s-native" % "3.2.4",
  "net.databinder.dispatch" %% "dispatch-core" % "0.10.1"
)

seq(org.scalatra.sbt.ScalatraPlugin.scalatraWithJRebel: _*)

resolvers ++= Seq(
  "Sonatype Nexus Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)
