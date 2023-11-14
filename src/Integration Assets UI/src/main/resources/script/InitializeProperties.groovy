import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {

    def prop = message.getProperties()

    def integrationFlowUriAll = []
    message.setProperty('integrationFlowUriAll',integrationFlowUriAll)

    def configurationsUriAll = []
    message.setProperty('configurationsUriAll', configurationsUriAll)

    def resourcesUriAll = []
    message.setProperty('resourcesUriAll', resourcesUriAll)

    def integrationFlowAll = []
    message.setProperty('integrationFlowAll', integrationFlowAll)

    def configurationsAll = []
    message.setProperty('configurationsAll', configurationsAll)

    def resourcesAll = []
    message.setProperty('resourcesAll', resourcesAll)

    return message
}
