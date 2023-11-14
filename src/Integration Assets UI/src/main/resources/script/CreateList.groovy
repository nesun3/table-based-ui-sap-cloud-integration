import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import java.text.SimpleDateFormat
import java.util.TimeZone

def Message processData(Message message) {

    def body = message.getBody(java.io.Reader)
    def jsonData = new JsonSlurper().parse(body)

    def prop = message.getProperties()
    List integrationFlowUriAll = prop.get("integrationFlowUriAll")

    jsonData.d.results.each { result ->
        integrationFlowUriAll.push(["packageId": result.Id, "packageName": result.Name, "integrationFlowUri": result.IntegrationDesigntimeArtifacts.__deferred.uri])
    }
//    println packageAll
//    println JsonOutput.toJson(packageAll)
//    println JsonOutput.prettyPrint(JsonOutput.toJson(packageAll))

    def integrationPackageId = prop.get("SelectIntegrationPackageIds")
    if (integrationPackageId && integrationPackageId.toString().trim().length() != 0) {

        def inputList = integrationPackageId.split(',')
        def filteredIntegrationFlowUri = []
        filteredIntegrationFlowUri = integrationFlowUriAll.findAll { iFlow -> inputList.contains(iFlow.packageId) }
        message.setProperty('integrationFlowUriAll', filteredIntegrationFlowUri)

    }
    return message
}

