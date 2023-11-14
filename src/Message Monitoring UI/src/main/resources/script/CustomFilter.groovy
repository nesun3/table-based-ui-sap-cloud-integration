import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {

    def messageLog = messageLogFactory.getMessageLog(message)
    def prop = message.getProperties()

    def fromDateTime = prop.get("FromDateTime")
    def modifiedFromDateTime = fromDateTime.toString().replace("Z", ".000")
    def toDateTime = prop.get("ToDateTime")
    def modifiedToDateTime = toDateTime.toString().replace("Z", ".000")
    def filterStatus = prop.get("Status")
    def top = prop.get("Top")
    def skip = prop.get("Skip")

    //Build custom query
    StringBuilder queryParams = new StringBuilder()
    queryParams << "\$filter=LogStart gt datetime\'$modifiedFromDateTime\' and LogEnd lt datetime\'$modifiedToDateTime\'"

    if(filterStatus && filterStatus.toString().trim().length() != 0)
        queryParams << " and Status eq \'"<< filterStatus.trim().toUpperCase()<<"\'"

    if(top && top.toString().trim().length() != 0)
        queryParams << "&\$top=$top"

    if(skip && skip.toString().trim().length() != 0)
        queryParams << "&\$skip=$skip"

    def orderItemsBy = "&\$orderby=LogStart desc"
    queryParams << orderItemsBy

    message.setProperty("queryParams", queryParams.toString())
    messageLog.addAttachmentAsString("QUERY PARAMS", queryParams.toString(), 'text/plain')

    return message
}
