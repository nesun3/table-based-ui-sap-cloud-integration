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
    List configurationsAll = prop.get("configurationsAll")
    // List resourcesAll = prop.get("resourcesAll")

    def output = JsonOutput.prettyPrint(JsonOutput.toJson(configurationsAll))
    // def output = JsonOutput.prettyPrint(JsonOutput.toJson(resourcesAll))
    def messageLog = messageLogFactory.getMessageLog(message)
    if (messageLog != null){
        messageLog.addAttachmentAsString('configurationsAll', output.toString(), 'text/plain')
        // messageLog.addAttachmentAsString('resourcesAll', output.toString(), 'text/plain')
    }
    return message
}

