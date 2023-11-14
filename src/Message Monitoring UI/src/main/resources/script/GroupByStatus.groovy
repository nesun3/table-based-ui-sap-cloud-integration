import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonOutput

def Message processData(Message message) {

    def prop = message.getProperties()
    List messageLogsAll = prop.get("messageLogsAll")

// Create a map to store the counts for different statuses
    def statusCounts = [:]

// Iterate through the messageLogsAll to count statuses
    messageLogsAll.each { item ->

        def packageName = item.PackageName
        def integrationFlowName = item.IntegrationFlowName
        def status = item.Status

        // Initialize the counts if they don't exist
        if (!statusCounts[packageName])
            statusCounts[packageName] = [:]
        if (!statusCounts[packageName][integrationFlowName])
            statusCounts[packageName][integrationFlowName] = [COMPLETED: 0, FAILED: 0, ESCALATED: 0, CANCELLED: 0, ABANDONED: 0]

        // Increment the corresponding status count
        statusCounts[packageName][integrationFlowName][status]++
    }

//    println(statusCounts)
//    println JsonOutput.prettyPrint(JsonOutput.toJson(statusCounts))

// Create the final output list of maps
    def output = []
// Iterate through the statusCounts map to create the output format
    statusCounts.each { packageName, integrationFlowsMap ->
        integrationFlowsMap.each { integrationFlowName, statusMap ->
            def entry = [
                    PackageName        : packageName,
                    IntegrationFlowName: integrationFlowName,
                    Completed          : statusMap.COMPLETED?:0,
                    Failed             : statusMap.FAILED?:0,
                    Escalated          : statusMap.ESCALATED?:0,
                    Cancelled          : statusMap.CANCELLED?:0,
                    Abandoned          : statusMap.ABANDONED?:0
            ]
            output << entry
        }
    }

//println(output)
//println JsonOutput.prettyPrint(JsonOutput.toJson(output))

    message.setProperty('messageLogsGroupByStatus', output)
    return message
}

