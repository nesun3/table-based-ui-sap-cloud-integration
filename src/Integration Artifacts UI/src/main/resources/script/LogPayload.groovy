import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonOutput

def Message logHTML(Message message) {

    def body = message.getBody(java.lang.String)
    def messageLog = messageLogFactory.getMessageLog(message)
    if (messageLog != null)
        messageLog.addAttachmentAsString('HTML', body, 'text/plain')

    return message
}

def Message logListAsJSON(Message message) {

    def body = message.getBody(java.lang.String)
    def prop = message.getProperties()
    List packageAll = prop.get("packageAll")
    // List integrationArtifactsAll = prop.get("integrationArtifactsAll")

    def output = JsonOutput.prettyPrint(JsonOutput.toJson(packageAll))
    // def output = JsonOutput.prettyPrint(JsonOutput.toJson(integrationArtifactsAll))
    def messageLog = messageLogFactory.getMessageLog(message)
    if (messageLog != null){
        messageLog.addAttachmentAsString('packageAll', output.toString(), 'text/plain')
        // messageLog.addAttachmentAsString('integrationArtifactsAll', output.toString(), 'text/plain')
    }
    return message
}

