import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import java.text.SimpleDateFormat
import java.util.TimeZone

def Message integrationFlowAll(Message message) {

    def body = message.getBody(java.io.Reader)
    def jsonData = new JsonSlurper().parse(body)

    def prop = message.getProperties()
    List integrationFlowAll = prop.get("integrationFlowAll")
    def packageName = prop.get("PackageName")

    jsonData.d.results.each { result ->

        integrationFlowAll.push([
                "packageId"             : result.PackageId,
                "packageName"           : packageName,
                "integrationFlowId"     : result.Id,
                "integrationFlowName"   : result.Name,
                "integrationFlowVersion": result.Version,
                "configurationsUri"     : result.Configurations.__deferred.uri,
                "resourcesUri"          : result.Resources.__deferred.uri
        ])
    }
//    println integrationFlowAll
//    println JsonOutput.toJson(integrationFlowAll)
//    println JsonOutput.prettyPrint(JsonOutput.toJson(integrationFlowAll))

    return message
}

def Message configurationsAll(Message message) {

    def body = message.getBody(java.io.Reader)
    def jsonData = new JsonSlurper().parse(body)

    def prop = message.getProperties()
    List configurationsAll = prop.get("configurationsAll")
    def packageName = prop.get("PackageName")
    def id = prop.get("Id")
    def name = prop.get("Name")
    def version = prop.get("Version")

    jsonData.d.results.each { result ->

        if(result.ParameterKey != "SAP_ProfileId"){
            configurationsAll.push([
                    "PackageName"        : packageName,
                    "Name"               : name,
                    "Id"                 : id,
                    "Version"            : version,
                    "ParameterKey"       : result.ParameterKey,
                    "ParameterValue"     : result.ParameterValue,
                    "DataType"           : result.DataType
            ])
        }

    }
//    println configurationsAll
//    println JsonOutput.toJson(configurationsAll)
//    println JsonOutput.prettyPrint(JsonOutput.toJson(configurationsAll))

    return message
}

def Message resourcesAll(Message message) {

    def body = message.getBody(java.io.Reader)
    def jsonData = new JsonSlurper().parse(body)

    def prop = message.getProperties()
    List resourcesAll = prop.get("resourcesAll")
    def packageName = prop.get("PackageName")
    def id = prop.get("Id")
    def name = prop.get("Name")
    def version = prop.get("Version")

    jsonData.d.results.each { result ->

        resourcesAll.push([
                "PackageName"        : packageName,
                "Name"               : name,
                "Id"                 : id,
                "Version"            : version,
                "ResourceType"       : getResourceType(result.ResourceType),
                "ResourceName"       : result.Name
        ])
    }
//    println resourcesAll
//    println JsonOutput.toJson(resourcesAll)
//    println JsonOutput.prettyPrint(JsonOutput.toJson(resourcesAll))

    return message
}

def getResourceType( def resource){

    def result
    switch (resource) {
        
        case "groovy":
            result = "Groovy Script"
            break

        case "js":
            result = "Java Script"
            break

        case "mmap":
            result = "Message Mapping"
            break

        case "opmap":
            result = "Operation Mapping"
            break

        case "xslt":
            result = "XSLT Mapping"
            break

        default:
            result = resource.toString().toUpperCase()
    }
    return result
}





