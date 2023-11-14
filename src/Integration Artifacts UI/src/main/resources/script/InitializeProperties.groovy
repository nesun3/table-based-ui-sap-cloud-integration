import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {

    def prop = message.getProperties()

    def packageAll = []
    message.setProperty('packageAll', packageAll)

    def integrationFlowUriAll = []
    message.setProperty('integrationFlowUriAll',integrationFlowUriAll)

    def valueMappingUriAll = []
    message.setProperty('valueMappingUriAll',valueMappingUriAll)

    def messageMappingUriAll = []
    message.setProperty('messageMappingUriAll',messageMappingUriAll)

    def scriptCollectionUriAll = []
    message.setProperty('scriptCollectionUriAll',scriptCollectionUriAll)

    def integrationArtifactsAll = []
    message.setProperty('integrationArtifactsAll',integrationArtifactsAll)

    return message
}
