import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import java.text.SimpleDateFormat
import java.util.TimeZone

def Message processData(Message message) {

    def prop = message.getProperties()
    List messageLogsAll = prop.get("messageLogsAll")

    def body = message.getBody(java.io.Reader)
    def jsonData = new JsonSlurper().parse(body)

    def allowedStatus = ["COMPLETED", "FAILED", "ESCALATED", "CANCELLED", "ABANDONED"]
    def filteredEntries = jsonData.d.results.findAll { entry -> allowedStatus.contains(entry.Status) }

    filteredEntries.each { result ->

        if (result.IntegrationArtifact.Type == "INTEGRATION_FLOW") {

            def logStartDate = result.LogStart
            def logEndDate = result.LogEnd
            def processingTime = calculateProcessingTime(logStartDate, logEndDate)

            messageLogsAll.push([
                    "PackageName"        : result.IntegrationArtifact.PackageName,
                    "IntegrationFlowName": result.IntegrationArtifact.Name,
                    "Status"             : result.Status,
                    "StartDateTime"      : logStartDate,
                    "EndDateTime"        : logEndDate,
                    "ProcessingTime"     : processingTime,
                    "ErrorText"          : "",
                    "MessageID"          : result.MessageGuid,
                    "MonitoringLink"     : result.AlternateWebLink
            ])

        }
    }
//    println messageLogsAll
//    println JsonOutput.toJson(messageLogsAll)
//    println JsonOutput.prettyPrint(JsonOutput.toJson(messageLogsAll))

    def integrationPackageId = prop.get("SelectIntegrationPackageIds")
    if (integrationPackageId && integrationPackageId.toString().trim().length() != 0) {

        def inputListByPackages = integrationPackageId.split(',')
        def filteredEntriesByPackages = []
        filteredEntriesByPackages = messageLogsAll.findAll { integrationPackage -> inputListByPackages.contains(integrationPackage.PackageName) }

        def integrationFlowId  = prop.get("SelectIntegrationFlowIds")
        if(integrationFlowId && integrationFlowId.toString().trim().length() != 0){
            def inputListByIntegrationFlow  = integrationFlowId.split(',')
            def filteredEntriesByIntegrationFlow = []
            filteredEntriesByIntegrationFlow = filteredEntriesByPackages.findAll { integrationFlow -> inputListByIntegrationFlow.contains(integrationFlow.IntegrationFlowName) }
            message.setProperty('messageLogsAll', filteredEntriesByIntegrationFlow)
        }
        else
            message.setProperty('messageLogsAll', filteredEntriesByPackages)

    }

    return message
}


def calculateProcessingTime(LogStartDate, LogEndDate) {

    def dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"))

    def logStartDate = dateFormat.parse(LogStartDate)
    def logEndDate = dateFormat.parse(LogEndDate)
    def processingTime  = logEndDate.time - logStartDate.time

    def hours = (processingTime / 3600000) as long
    def minutes = ((processingTime % 3600000) / 60000) as long
    def seconds = ((processingTime % 60000) / 1000) as long
    def milliseconds = processingTime % 1000 as long

    // Format the output as "hh:mm:ss:SSS"
    def formattedTime = String.format("%02d:%02d:%02d:%03d", hours, minutes, seconds, milliseconds)

    return formattedTime
}
