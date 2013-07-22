package com.parascal.scalatra.swagger.test

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext
import org.eclipse.jetty.servlet.DefaultServlet
import org.scalatra.servlet.ScalatraListener
import java.net.InetSocketAddress
import org.eclipse.jetty.servlet.DefaultServlet

object JettyBoot {
 
  def setupJettyServer: Server = {
    val server = new Server(new InetSocketAddress("0.0.0.0", 8080))
    val context = new WebAppContext()
    context setContextPath "/"
    context.setResourceBase("src/main/webapp")
    context.setInitParameter(ScalatraListener.LifeCycleKey, "com.parascal.scalatra.swagger.test.ScalatraBootstrap")
    context.addEventListener(new ScalatraListener)
    context.addServlet(classOf[DefaultServlet], "/")
  
    server.setHandler(context) 
    
    server
  }
  
  def main(args: Array[String]) {
    val server = setupJettyServer
    server.start
    server.join
  }

}