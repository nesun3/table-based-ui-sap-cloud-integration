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
    List messageLogsAll = prop.get("messageLogsAll")
    // List messageLogsGroupByStatus = prop.get("messageLogsGroupByStatus")

    def output = JsonOutput.prettyPrint(JsonOutput.toJson(messageLogsAll))
    // def output = JsonOutput.prettyPrint(JsonOutput.toJson(messageLogsGroupByStatus))
    def messageLog = messageLogFactory.getMessageLog(message)
    if (messageLog != null){
        messageLog.addAttachmentAsString('messageLogsAll', output.toString(), 'text/plain')
        // messageLog.addAttachmentAsString('messageLogsGroupByStatus', output.toString(), 'text/plain')
    }
    return message
}

