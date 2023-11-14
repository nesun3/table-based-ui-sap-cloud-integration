import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {

    def prop = message.getProperties()

    def messageLogsAll = []
    message.setProperty('messageLogsAll', messageLogsAll)

    def groupBy = prop.get("GroupBy")
    if(groupBy && groupBy.toString().trim().length() != 0){
        def messageLogsGroupByStatus = []
        message.setProperty('messageLogsGroupByStatus', messageLogsGroupByStatus)
    }

    return message
}
