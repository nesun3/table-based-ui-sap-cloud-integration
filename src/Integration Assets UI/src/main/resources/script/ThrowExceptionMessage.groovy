import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {

    def prop = message.getProperties()

    def host = prop.get("HostAddress")
    if(host == null || host.trim().length() == 0 || host.trim() == '""')
        throw new Exception("\'Error: Host URL of Process Integration Runtime Service | API Plan is missing.\'")

    def oAuthCredentialAlias = prop.get("OAuthCredential")
    if(oAuthCredentialAlias == null || oAuthCredentialAlias.trim().length() == 0 || oAuthCredentialAlias.trim() == '""')
        throw new Exception("\'Error: Name of the credential created in Security Material for SAP CI ODATA V2 API authentication is missing.\'")

    def integrationFlowId  = prop.get("SelectIntegrationFlowIds")
    if(integrationFlowId && integrationFlowId.toString().trim().length() != 0){
        def integrationPackageId = prop.get("SelectIntegrationPackageIds")
        if(integrationPackageId == null || integrationPackageId.trim().length() == 0 || integrationPackageId.trim() == '""')
            throw new Exception("\'Error: Integration Package Id's of the corresponding Integration Flow Id's  is missing\'")
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

    def filterBy = prop.get("FilterBy")
    if(filterBy == null || filterBy.trim().length() == 0 || filterBy.trim() == '""')
        throw new Exception("\'Error: FilterBy is missing. Allowed values: conf or res.\'")
    if(filterBy && filterBy.toString().trim().length() != 0){
        def validValues = ["CONF", "RES"]
        if(!validValues.contains(filterBy.toString().trim().toUpperCase()))
            throw new Exception("\'Error: Value \'$filterBy\' for FilterBy is not valid. Allowed values: conf or res.\'")
    }

    return message
}








