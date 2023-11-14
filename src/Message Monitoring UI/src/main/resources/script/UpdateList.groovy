import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {

    def prop = message.getProperties()
    def messageID = prop.get("MPL_ID")
    List messageLogsAll = prop.get("messageLogsAll")

    def body = message.getBody(String)
    if(body){
        body = body.replaceAll('\n', '')

        def searchItem = messageLogsAll.find { it.MessageID == messageID }
        if (searchItem)
            searchItem.ErrorText = body.length() < 400?"$body":"${body[0..399]}"
    }

    return message
}





