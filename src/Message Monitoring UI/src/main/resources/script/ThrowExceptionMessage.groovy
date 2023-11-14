import com.sap.gateway.ip.core.customdev.util.Message
import com.sap.it.api.ITApiFactory
import com.sap.it.api.securestore.SecureStoreService
import java.text.SimpleDateFormat

def Message processData(Message message) {

    def prop = message.getProperties()

    def host = prop.get("HostAddress")
    if(host == null || host.trim().length() == 0 || host.trim() == '""')
        throw new Exception("\'Error: Host URL of Process Integration Runtime Service | API Plan is missing.\'")

    def oAuthCredentialAlias = prop.get("OAuthCredential")
    if(oAuthCredentialAlias == null || oAuthCredentialAlias.trim().length() == 0 || oAuthCredentialAlias.trim() == '""')
        throw new Exception("\'Error: Name of the credential created in Security Material for SAP CI ODATA V2 API authentication is missing.\'")

    def fromDateTime = prop.get("FromDateTime")
    def toDateTime = prop.get("ToDateTime")
    if((fromDateTime == null || fromDateTime.trim().length() == 0 || fromDateTime.trim() == '""') && (toDateTime == null || toDateTime.trim().length() == 0 || toDateTime.trim() == '""')){
        def past24Hours = prop.get("Past_24_Hours_Ago")
        message.setProperty('FromDateTime',past24Hours)
        def currentTime = prop.get("Current_DateTime")
        message.setProperty('ToDateTime',currentTime)

    }
    else if( ((fromDateTime && fromDateTime.toString().trim().length() != 0) && (toDateTime == null || toDateTime.trim().length() == 0 || toDateTime.trim() == '""'))
            || ((toDateTime && toDateTime.toString().trim().length() != 0) && (fromDateTime == null || fromDateTime.trim().length() == 0 || fromDateTime.trim() == '""')) )
        throw new Exception("\'Error: Both FromDateTime and ToDateTime must be provided in yyyy-MM-ddThh:mm:ssZ format or left empty. Timezone should be in UTC.\'")
    else{
        if((fromDateTime.toString().trim().length() != 20) || (toDateTime.toString().trim().length() != 20))
            throw new Exception("\'Error: Both FromDateTime must be 20 characters long and should be in yyyy-MM-ddThh:mm:ssZ format only. Timezone should be UTC.\'")

        if(fromDateTime > toDateTime)
            throw new Exception("\'Error: FromDateTime must be less than ToDateTime. Timezone should be UTC.\'")

        def difference = calculateDateDifference(fromDateTime, toDateTime)
        message.setProperty('dateDifference',difference)
    }

    def filterStatus = prop.get("Status")
    if(filterStatus && filterStatus.toString().trim().length() != 0){
        def validStatusType = ["COMPLETED","FAILED","ESCALATED","CANCELLED","ABANDONED"]
        if (!validStatusType.contains(filterStatus.trim().toUpperCase()))
            throw new Exception("\'Error: Value for message processing logs with status \'$filterStatus\' is not valid. Allowed values: completed,failed,escalated,cancelled,abandoned.\'")
    }

    def integrationFlowId  = prop.get("SelectIntegrationFlowIds")
    if(integrationFlowId && integrationFlowId.toString().trim().length() != 0){
        def integrationPackageId = prop.get("SelectIntegrationPackageIds")
        if(integrationPackageId == null || integrationPackageId.trim().length() == 0 || integrationPackageId.trim() == '""')
            throw new Exception("\'Error: Integration Package Id's of the corresponding Integration Flow Id's  is missing.\'")
    }

    def integrationPackageId = prop.get("SelectIntegrationPackageIds")
    if(integrationPackageId && integrationPackageId.trim().length() != 0){
        def top = prop.get("Top")
        if(top && top.trim().length() != 0)
            throw new Exception("\'Error: If Integration Package Id's exits then TOP should set as blank.\'")
        def skip = prop.get("Skip")
        if(skip && skip.trim().length() != 0)
            throw new Exception("\'Error: If Integration Package Id's exits then SKIP should set as blank.\'")
    }

    def groupBy = prop.get("GroupBy")
    if(groupBy && groupBy.toString().trim().length() != 0){
        if(groupBy.toString().toUpperCase() != "STATUS")
            throw new Exception("\'Error: Value \'$groupBy\' for GroupBy is not valid. Allowed values: status.\'")
    }


    return message
}

def calculateDateDifference(fromDateTime, toDateTime) {
    def dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    def fromDate = dateFormat.parse(fromDateTime)
    def toDate = dateFormat.parse(toDateTime)

    def differenceInMillis = toDate.time - fromDate.time
    def daysDifference = differenceInMillis / (1000 * 60 * 60 * 24)

    if (daysDifference > 90)
        throw new Exception("Difference between FromDateTime and ToDateTime is more than 90 days.")

    return daysDifference
}







