import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {

    def prop = message.getProperties()

    def host = prop.get("HostAddress")
    if(host == null || host.trim().length() == 0 || host.trim() == '""')
        throw new Exception("\'Error: Host URL of Process Integration Runtime Service | API Plan is missing.\'")

    def oAuthCredentialAlias = prop.get("OAuthCredential")
    if(oAuthCredentialAlias == null || oAuthCredentialAlias.trim().length() == 0 || oAuthCredentialAlias.trim() == '""')
        throw new Exception("\'Error: Name of the credential created in Security Material for SAP CI ODATA V2 API authentication is missing.\'")

    def integrationContentType  = prop.get("SelectArtifactType")
    if(integrationContentType == null || integrationContentType.trim().length() == 0 || integrationContentType.trim() == '""')
        throw new Exception("\'Error: Select Artifact Type is missing. Allowed values: all,ifl,vmp,mmc,src.\'")
    if(integrationContentType && integrationContentType.toString().trim().length() != 0){
        def validIntContentType = ["IFL", "VMP", "MMC", "SRC", "ALL"]
        def inputValues = integrationContentType.split(',')
        inputValues.each { value ->
            def trimmedValue = value.trim().toUpperCase()
            if (!validIntContentType.contains(trimmedValue))
                throw new Exception("\'Error: Value for Select Artifact Type is not valid. Allowed values: all,ifl,vmp,mmc,src.\'")
        }
    }

    def integrationFlowId  = prop.get("SelectIntegrationFlowIds")
    if(integrationFlowId && integrationFlowId.toString().trim().length() != 0){
        def integrationPackageId = prop.get("SelectIntegrationPackageIds")
        if(integrationPackageId == null || integrationPackageId.trim().length() == 0 || integrationPackageId.trim() == '""')
            throw new Exception("\'Error: Integration Package Id's of the corresponding Integration Flow Id's  is missing\'")
        def inputValues = integrationContentType.split(',').collect { it.trim().toUpperCase() }
        if (!inputValues.contains('ALL') && !inputValues.contains('IFL')) {
            throw new Exception("\'Error: If Integration Flow Id's exists, then Value for Select Artifact Type to be either all or ifl.\'")
        }
    }

    def valueMappingId  = prop.get("SelectValueMappingIds")
    if(valueMappingId && valueMappingId.toString().trim().length() != 0){
        def integrationPackageId = prop.get("SelectIntegrationPackageIds")
        if(integrationPackageId == null || integrationPackageId.trim().length() == 0 || integrationPackageId.trim() == '""')
            throw new Exception("\'Error: Integration Package Id's of the corresponding Value Mapping Id's is missing\'")
        def inputValues = integrationContentType.split(',').collect { it.trim().toUpperCase() }
        if (!inputValues.contains('ALL') && !inputValues.contains('VMP')) {
            throw new Exception("\'Error: If Value Mapping Id's exists, then Value for Select Artifact Type to be either all or vmp.\'")
        }
    }

    def messageMappingId  = prop.get("SelectMessageMappingIds")
    if(messageMappingId && messageMappingId.toString().trim().length() != 0){
        def integrationPackageId = prop.get("SelectIntegrationPackageIds")
        if(integrationPackageId == null || integrationPackageId.trim().length() == 0 || integrationPackageId.trim() == '""')
            throw new Exception("\'Error: Integration Package Id's of the corresponding Message Mapping Id's is missing\'")
        def inputValues = integrationContentType.split(',').collect { it.trim().toUpperCase() }
        if (!inputValues.contains('ALL') && !inputValues.contains('MMC')) {
            throw new Exception("\'Error: If Message Mapping Id's exists, then Value for Select Artifact Type to be either all or mmc.\'")
        }
    }

    def scriptCollectionId  = prop.get("SelectScriptCollectionIds")
    if(scriptCollectionId && scriptCollectionId.toString().trim().length() != 0){
        def integrationPackageId = prop.get("SelectIntegrationPackageIds")
        if(integrationPackageId == null || integrationPackageId.trim().length() == 0 || integrationPackageId.trim() == '""')
            throw new Exception("\'Error: Integration Package Id's of the corresponding Script Collection Id's is missing\'")
        def inputValues = integrationContentType.split(',').collect { it.trim().toUpperCase() }
        if (!inputValues.contains('ALL') && !inputValues.contains('SRC')) {
            throw new Exception("\'Error: If Script Collection Id's exists, then Value for Select Artifact Type to be either all or src.\'")
        }
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
        if(groupBy.toString().toUpperCase() != "COUNT")
            throw new Exception("\'Error: Value \'$groupBy\' for GroupBy is not valid. Allowed values: count.\'")
    }

    return message
}








