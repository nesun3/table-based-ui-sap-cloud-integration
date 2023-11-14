import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.MarkupBuilder

def Message processData(Message message) {

    def body = message.getBody(java.io.Reader)

    def prop = message.getProperties()
    List messageLogsAll = prop.get("messageLogsAll")

    Writer writer = new StringWriter()
    def indentPrinter = new IndentPrinter(writer, ' ')
    def builder = new MarkupBuilder(indentPrinter)

    builder.MessageProcessingErrorLogs{
        messageLogsAll.each{  messageLog->
            if(messageLog.Status != "COMPLETED"){
                MessageProcessingErrorLog{
                    MessageID(messageLog.MessageID)
                    Status(messageLog.Status)
                }
            }
        }
    }

    message.setBody(writer.toString())
    return message
}

