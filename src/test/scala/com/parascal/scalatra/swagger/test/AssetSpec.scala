package com.parascal.scalatra.swagger.test

import org.eclipse.jetty.server.Server
import org.scalatest.BeforeAndAfterAll
import org.scalatest.FunSpec

import dispatch._
import Defaults._

import org.json4s._
import org.json4s.native.JsonMethods._

class AssetSpec extends FunSpec with BeforeAndAfterAll {
  
  import scala.concurrent.ExecutionContext.global

  var server: Server = null
  var assetService = null
  
  override def beforeAll {
    server = JettyBoot.setupJettyServer
    server.start
  }
  
  describe("An Asset") {
    it("should return the id used in the route") {
      val id = 10
      val assetApi = url(s"http://localhost:8080/assets/$id")
      val assetResult = Http(assetApi OK as.String)
      val resultId = assetResult().toLong
      
      assert(id === resultId)
    }
    it("should return a description for the assets resource path"){
      val description = (getAssetSwaggerJson \ "description").asInstanceOf[JString].s
      assert(description === AssetServlet.Description)
    }
    it("should return a description for the user in the asset model") {
      val descriptionList = for { 
        JObject(assetApi) <- getAssetSwaggerJson
        JField("models", JObject(models)) <- assetApi
        JField("Asset", JObject(asset)) <- models
        JField("properties", JObject(props)) <- asset
        JField("user", JObject(user)) <- props
        JField("description", JString(description)) <- user
      } yield description
      assert(descriptionList.size === 1)
      assert(descriptionList.head === Asset.UserDescription)
    }
  }
  
  private def getAssetSwaggerJson: JValue = {
    val assetSwaggerApi = url("http://localhost:8080/swagger-specs/assets.json")
    val assetSwaggerResult = Http(assetSwaggerApi OK as.String)
    parse(assetSwaggerResult())
  }
  override def afterAll { 
    server.stop 
    server.join
  }
}