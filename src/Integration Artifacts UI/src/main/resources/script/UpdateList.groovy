import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import java.text.SimpleDateFormat
import java.util.TimeZone

def Message integrationFlowUriAll(Message message) {

    def body = message.getBody(java.io.Reader)
    def jsonData = new JsonSlurper().parse(body)

    def prop = message.getProperties()
    List integrationArtifactsAll = prop.get("integrationArtifactsAll")
    def packageName = prop.get("PackageName")

    jsonData.d.results.each { result ->

        def creationDate = parseJSONDate(result.CreatedAt)
        def modifiedDate = parseJSONDate(result.ModifiedAt)

        integrationArtifactsAll.push([

                "PackageName"   : packageName,
                "PackageId"     : result.PackageId,
                "ArtifactType"  : "IntegrationFlow",
                "Name"          : result.Name,
                "Id"            : result.Id,
                "Version"       : result.Version,
                "Description"   : result.Description,
                "LastModifiedBy": result.ModifiedBy,
                "LastModifiedAt": "$modifiedDate UTC",
                "CreatedBy"     : result.CreatedBy,
                "CreatedAt"     : "$creationDate UTC"

        ])
    }
//    println integrationArtifactsAll
//    println JsonOutput.toJson(integrationArtifactsAll)
//    println JsonOutput.prettyPrint(JsonOutput.toJson(integrationArtifactsAll))

    return message
}

def Message valueMappingUriAll(Message message) {

    def body = message.getBody(java.io.Reader)
    def jsonData = new JsonSlurper().parse(body)

    def prop = message.getProperties()
    List integrationArtifactsAll = prop.get("integrationArtifactsAll")
    def packageName = prop.get("PackageName")

    jsonData.d.results.each { result ->

        integrationArtifactsAll.push([

                "PackageName"   : packageName,
                "PackageId"     : result.PackageId,
                "ArtifactType"  : "ValueMapping",
                "Name"          : result.Name,
                "Id"            : result.Id,
                "Version"       : result.Version,
                "Description"   : result.Description,
                "LastModifiedBy": "",
                "LastModifiedAt": "",
                "CreatedBy"     : "",
                "CreatedAt"     : ""
        ])
    }
//    println integrationArtifactsAll
//    println JsonOutput.toJson(integrationArtifactsAll)
//    println JsonOutput.prettyPrint(JsonOutput.toJson(integrationArtifactsAll))

    return message
}

def Message messageMappingUriAll(Message message) {

    def body = message.getBody(java.io.Reader)
    def jsonData = new JsonSlurper().parse(body)

    def prop = message.getProperties()
    List integrationArtifactsAll = prop.get("integrationArtifactsAll")
    def packageName = prop.get("PackageName")

    jsonData.d.results.each { result ->

        integrationArtifactsAll.push([

                "PackageName"   : packageName,
                "PackageId"     : result.PackageId,
                "ArtifactType"  : "MessageMapping",
                "Name"          : result.Name,
                "Id"            : result.Id,
                "Version"       : result.Version,
                "Description"   : result.Description,
                "LastModifiedBy": "",
                "LastModifiedAt": "",
                "CreatedBy"     : "",
                "CreatedAt"     : ""
        ])
    }
//    println integrationArtifactsAll
//    println JsonOutput.toJson(integrationArtifactsAll)
//    println JsonOutput.prettyPrint(JsonOutput.toJson(integrationArtifactsAll))

    return message
}

def Message scriptCollectionUriAll(Message message) {

    def body = message.getBody(java.io.Reader)
    def jsonData = new JsonSlurper().parse(body)

    def prop = message.getProperties()
    List integrationArtifactsAll = prop.get("integrationArtifactsAll")
    def packageName = prop.get("PackageName")

    jsonData.d.results.each { result ->

        integrationArtifactsAll.push([

                "PackageName"   : packageName,
                "PackageId"     : result.PackageId,
                "ArtifactType"  : "ScriptCollection",
                "Name"          : result.Name,
                "Id"            : result.Id,
                "Version"       : result.Version,
                "Description"   : result.Description,
                "LastModifiedBy": "",
                "LastModifiedAt": "",
                "CreatedBy"     : "",
                "CreatedAt"     : ""
        ])
    }
//    println integrationArtifactsAll
//    println JsonOutput.toJson(integrationArtifactsAll)
//    println JsonOutput.prettyPrint(JsonOutput.toJson(integrationArtifactsAll))

    return message
}


def parseJSONDate(jsonDate) {

    def dateFormat = new SimpleDateFormat("MMM dd, yyyy, HH:mm:ss")
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    def date = new Date(Long.parseLong(jsonDate))
    def formattedDate = dateFormat.format(date)

    return formattedDate
}





