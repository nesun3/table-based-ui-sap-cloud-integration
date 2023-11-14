import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import java.text.SimpleDateFormat
import java.util.TimeZone

def Message processData(Message message) {

    def body = message.getBody(java.io.Reader)
    def jsonData = new JsonSlurper().parse(body)

    def prop = message.getProperties()
    List packageAll = prop.get("packageAll")
    List integrationFlowUriAll = prop.get("integrationFlowUriAll")
    List valueMappingUriAll = prop.get("valueMappingUriAll")
    List messageMappingUriAll = prop.get("messageMappingUriAll")
    List scriptCollectionUriAll = prop.get("scriptCollectionUriAll")

    jsonData.d.results.each { result ->

        def creationDate = parseJSONDate(result.CreationDate)
        def modifiedDate = parseJSONDate(result.ModifiedDate)

        packageAll.push([
                "Name": result.Name,
                "Id": result.Id,
                "Version": result.Version,
                "ShortText": result.ShortText,
                "Vendor": result.Vendor,
                "PartnerContent":  result.PartnerContent,
                "UpdateAvailable":  result.UpdateAvailable,
                "Mode": result.Mode,
                "CreatedBy": result.CreatedBy,
                "CreatedDate": "$creationDate UTC",
                "LastModifiedBy": result.ModifiedBy,
                "LastModifiedDate": "$modifiedDate UTC",
                "IntegrationFlows": "",
                "ValueMappings": "",
                "MessageMappings": "",
                "ScriptCollections": "",
        ])
        integrationFlowUriAll.push(["packageId": result.Id, "packageName": result.Name, "integrationFlowUri": result.IntegrationDesigntimeArtifacts.__deferred.uri])
        valueMappingUriAll.push(["packageId": result.Id,"packageName": result.Name, "valueMappingUri": result.ValueMappingDesigntimeArtifacts.__deferred.uri])
        messageMappingUriAll.push(["packageId": result.Id, "packageName": result.Name, "messageMappingUri": result.MessageMappingDesigntimeArtifacts.__deferred.uri])
        scriptCollectionUriAll.push(["packageId": result.Id, "packageName": result.Name, "scriptCollectionUri": result.ScriptCollectionDesigntimeArtifacts.__deferred.uri])
    }
//    println packageAll
//    println JsonOutput.toJson(packageAll)
//    println JsonOutput.prettyPrint(JsonOutput.toJson(packageAll))

    def integrationPackageId = prop.get("SelectIntegrationPackageIds")
    if (integrationPackageId && integrationPackageId.toString().trim().length() != 0) {
        def inputList = integrationPackageId.split(',')

        def filteredPackages = []
        filteredPackages = packageAll.findAll { integrationPackage -> inputList.contains(integrationPackage.Id) }
        message.setProperty('packageAll', filteredPackages)

        def filteredIntegrationFlowUri = []
        filteredIntegrationFlowUri = integrationFlowUriAll.findAll { iFlow -> inputList.contains(iFlow.packageId) }
        message.setProperty('integrationFlowUriAll', filteredIntegrationFlowUri)

        def filteredValueMappingUri = []
        filteredValueMappingUri = valueMappingUriAll.findAll { valueMap -> inputList.contains(valueMap.packageId) }
        message.setProperty('valueMappingUriAll', filteredValueMappingUri)

        def filteredMessageMappingUri = []
        filteredMessageMappingUri = messageMappingUriAll.findAll { mmap -> inputList.contains(mmap.packageId) }
        message.setProperty('messageMappingUriAll', filteredMessageMappingUri)

        def filteredScriptCollectionUriAll = []
        filteredScriptCollectionUriAll = scriptCollectionUriAll.findAll { script -> inputList.contains(script.packageId) }
        message.setProperty('scriptCollectionUriAll', filteredScriptCollectionUriAll)
    }

    return message
}

def parseJSONDate(jsonDate){

    def dateFormat = new SimpleDateFormat("MMM dd, yyyy, HH:mm:ss")
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    def date = new Date(Long.parseLong(jsonDate))
    def formattedDate = dateFormat.format(date)

    return formattedDate
}
