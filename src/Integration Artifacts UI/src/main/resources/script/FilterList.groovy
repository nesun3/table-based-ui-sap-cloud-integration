import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

def Message processData(Message message) {

    def prop = message.getProperties()
    List integrationArtifactsAll = prop.get("integrationArtifactsAll")

    def integrationFlowId  = prop.get("SelectIntegrationFlowIds")
    if(integrationFlowId && integrationFlowId.toString().trim().length() != 0){

        def inputList = integrationFlowId.split(',')
        def filteredIntegrationFlows = []
        filteredIntegrationFlows = integrationArtifactsAll.findAll { iFlow -> inputList.contains(iFlow.Id) }
        message.setProperty('integrationArtifactsAll',filteredIntegrationFlows)
    }

    def valueMappingId  = prop.get("SelectValueMappingIds")
    if(valueMappingId && valueMappingId.toString().trim().length() != 0){

        def inputList = valueMappingId.split(',')
        def filteredValueMappings = []
        filteredValueMappings = integrationArtifactsAll.findAll { valueMap -> inputList.contains(valueMap.Id) }
        message.setProperty('integrationArtifactsAll',filteredValueMappings)
    }

    def messageMappingId  = prop.get("SelectMessageMappingIds")
    if(messageMappingId && messageMappingId.toString().trim().length() != 0){

        def inputList = messageMappingId.split(',')
        def filteredMessageMappings = []
        filteredMessageMappings = integrationArtifactsAll.findAll { mmap -> inputList.contains(mmap.Id) }
        message.setProperty('integrationArtifactsAll',filteredMessageMappings)
    }

    def scriptCollectionId  = prop.get("SelectScriptCollectionIds")
    if(scriptCollectionId && scriptCollectionId.toString().trim().length() != 0){

        def inputList = scriptCollectionId.split(',')
        def filteredScriptCollections = []
        filteredScriptCollections = integrationArtifactsAll.findAll { script -> inputList.contains(script.Id) }
        message.setProperty('integrationArtifactsAll',filteredScriptCollections)
    }

    return message

}
