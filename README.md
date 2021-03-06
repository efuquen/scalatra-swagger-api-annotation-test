Test case to test ApiProperty extraction.

Currenty test case fails when checking description in the Asset mode, which is suppose to be set by swagger ApiProperty annotation.  Just run sbt test and the relevant failure message is:

```
[info] - should return a description for the user in the asset model *** FAILED **
```

The issue is that the description property is "null" when doing a get request on the swagger assets.json file for the locally hosted server.  You can run the server by starting via the JettyBoot class, and the relevant output is like so:

```
{
...
  models: {
    Asset: {
      id: "Asset",
      description: "Asset",
      properties: {
      user: {
        description: null,
        required: true,
        type: "string"
      },
      masterId: {
        description: null,
        required: true,
        type: "string"
      }
    }
    }
  },
...
}
```
http://localhost:8080/swagger-specs/assets.json

The correct output should be "User that created this asset", as specified on this line: 

https://github.com/efuquen/scalatra-swagger-api-annotation-test/blob/master/src/main/scala/com/parascal/scalatra/swagger/test/AssetServlet.scala#L38

Though it's not tested for in the test case nothing is set for any of the annotations (i.e. the ApiClass description or the allowable values).
