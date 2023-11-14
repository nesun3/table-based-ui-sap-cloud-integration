import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonOutput

def Message processData(Message message) {

    def prop = message.getProperties()
    List integrationArtifactsAll = prop.get("integrationArtifactsAll")

// Create a map to store the counts for different statuses
    def countArtifacts = [:]

// Iterate through the messageLogsAll to count statuses
    integrationArtifactsAll.each { item ->

        def packageName = item.PackageName
        def packageId = item.PackageId
        def artifactType = item.ArtifactType

        // Initialize the counts if they don't exist
        if (!countArtifacts[packageName])
            countArtifacts[packageName] = [:]
        if (!countArtifacts[packageName][packageId])
            countArtifacts[packageName][packageId] = [IntegrationFlow: 0, ValueMapping: 0, MessageMapping: 0, ScriptCollection: 0]

        // Increment the corresponding status count
        countArtifacts[packageName][packageId][artifactType]++
    }

//    println(countArtifacts)
//    println JsonOutput.prettyPrint(JsonOutput.toJson(countArtifacts))

    List packageAll = prop.get("packageAll")

    countArtifacts.each{ packageName, packageIdMap ->
        packageIdMap.each{  packageId, countArtifactMaps ->
            def searchItem = packageAll.find{ it.Id == packageId}
            if(searchItem){
                searchItem.IntegrationFlows = countArtifactMaps.IntegrationFlow?:0
                searchItem.ValueMappings = countArtifactMaps.ValueMapping?:0
                searchItem.MessageMappings = countArtifactMaps.MessageMapping?:0
                searchItem.ScriptCollections = countArtifactMaps.ScriptCollection?:0
            }
        }

    }

    message.setProperty('packageAll', packageAll)
    return message
}

