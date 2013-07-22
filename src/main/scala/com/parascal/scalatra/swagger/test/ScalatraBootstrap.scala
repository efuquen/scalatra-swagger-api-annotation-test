package com.parascal.scalatra.swagger.test

import javax.servlet.ServletContext
import org.scalatra.LifeCycle
import org.scalatra.swagger.Swagger

class ScalatraBootstrap extends LifeCycle {
  
  implicit val swagger = new Swagger("1.0", "1")
    
  override def init(context: ServletContext) {
    context.mount(new AssetServlet, "/assets/*")
    context.mount(new SwaggerResources, "/swagger-specs/*")
  } 
}