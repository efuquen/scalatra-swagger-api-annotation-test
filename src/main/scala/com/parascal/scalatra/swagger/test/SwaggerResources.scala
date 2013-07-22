package com.parascal.scalatra.swagger.test

import org.scalatra.ScalatraServlet
import org.scalatra.swagger.Swagger
import org.scalatra.swagger.NativeSwaggerBase
import org.json4s.Formats
import org.json4s.DefaultFormats

class SwaggerResources(implicit val swagger: Swagger) extends ScalatraServlet with NativeSwaggerBase {
  implicit override val jsonFormats: Formats = DefaultFormats
}