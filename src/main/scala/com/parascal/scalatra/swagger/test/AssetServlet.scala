package com.parascal.scalatra.swagger.test

import org.scalatra.json._
import org.scalatra.swagger.SwaggerSupport
import org.scalatra.FutureSupport
import org.scalatra.ScalatraServlet
import org.scalatra.Ok
import org.scalatra.swagger.Swagger
import com.wordnik.swagger.annotations.ApiProperty
import com.wordnik.swagger.annotations.ApiClass

object AssetServlet {
  final val Description = "The Assets API."
}

class AssetServlet(
  implicit val swagger: Swagger
) extends ScalatraServlet with SwaggerSupport {
    
  override protected val applicationName = Some("assets")
  protected val applicationDescription = AssetServlet.Description
  
  val getAsset = (apiOperation[Asset]("getAsset")
    summary "Show a single asset."
    notes "Will display the contents of an asset specified by the provided Asset ID."
    parameters (
        pathParam[String]("assetId").description("Asset ID of the Asset to be sent.")
    )
  )
  
  get("/:id", operation(getAsset)) {
    val assetId = params("id")
    Ok(assetId) 
  }
}

object Asset {
  final val UserDescription = "User that created this asset" 
}

@ApiClass(value = "Asset Model", description = "This is the class to model an Asset")
case class Asset(
  @ApiProperty(value = Asset.UserDescription, allowableValues = "ed") user: String
)
