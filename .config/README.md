## Prerequisite

1. Download the Integration Package Zip file from the GitHub Repo and import Integration Package into your tenant.
2. Create an OAuth client (Client Credentials grant type) on the tenant with service plan **‘api’**.
    - Navigate to your SAP BTP cockpit of the Cloud Foundry subaccount, go to 'Services', then 'Service Marketplace' and select 'Process Integration'.
    - Create a new instance with service plan **'api'** and with the following configuration:
        - Grant-types: 'client_credentials'
        - Roles: `AuthGroup_IntegrationDeveloper`, `AuthGroup_Administrator`, `AuthGroup_BusinessExpert`
    - Create a Service Key and copy the entire JSON text to your clipboard to use in the Step 3 for Create `OAuth2 Client Credentials`. *This credentials will be used to call public ODATA APIs of the SAP Cloud Integration to fetch the required Integration Content or Message Processing Logs API.*     
>  For the Neo Environment, please refer to steps 1 and 2 in the [Neo documentation](https://help.sap.com/docs/cloud-integration/sap-cloud-integration/setting-up-oauth-inbound-authentication-with-client-credentials-grant-for-api-clients). Assign the roles mentioned above to the user 'oauth_client_<client ID>'. Copy the Token Endpoint (found in the branding tab), Client ID, and Client Secret to your clipboard for use in Step 3 for Create `OAuth2 Client Credentials`. **[Subscription: Select tmn node]**
3. Create the User Credentials in SAP Cloud Integration.
    - Manage `Security Material` -> Add Create `OAuth2 Client Credentials`. **Deploy** the credentials.
4. Create an OAuth client (Client Credentials grant type) on the tenant with service plan **‘integration-flow’**. 
    - Navigate to your SAP BTP cockpit of the Cloud Foundry subaccount, go to 'Services', then 'Service Marketplace' and select 'Process Integration'.
    - Create a new instance with service plan **'integration-flow'** and with the following configuration:
        - Grant-types: 'client_credentials'
        - Roles: `ESBMessaging.send`
    - Create a Service Key and copy the entire JSON text to your clipboard to use in the Step 6 for `Configure Environments`. *This credentials will be used by the REST API Client to connect to the http endpoints of the iflows.*     
>  For the Neo Environment, please refer to steps 1 and 2 in the [Neo documentation](https://help.sap.com/docs/cloud-integration/sap-cloud-integration/oauth-client-credentials-grant#configuring-oauth-with-a-client-credentials-grant). Assign the roles mentioned above to the user 'oauth_client_<client ID>'. Copy the Token Endpoint (found in the branding tab), Client ID, and Client Secret to your clipboard for use in Step 6 for `Configure Environments`. **[Subscription: Select iflmap node]**
5. Based on REST API client of your preference – import as below
   - For **Postman**, download the Postman Collection Zip file from the GitHub Repo. Unzip and import the collections and environment into your Postman.
      ![postman collection](https://github.com/nesun3/table-based-ui-sap-cloud-integration/assets/93763073/8c58673e-0d27-42de-a6de-9cf95eec2512)
      ![postman environments](https://github.com/nesun3/table-based-ui-sap-cloud-integration/assets/93763073/36dda688-c27a-4340-8779-fd075945895a)
    
   - For **Insomnia**, download the Insomnia Collection Zip file from the GitHub Repo. Unzip and import the collections and environment into your Insomnia.
     ![insomnia collection](https://github.com/nesun3/table-based-ui-sap-cloud-integration/assets/93763073/3f24158c-cc17-4a87-b180-7be77dd67583)
     ![insomnia environments](https://github.com/nesun3/table-based-ui-sap-cloud-integration/assets/93763073/775e738b-da39-4597-b482-548bfa81e518)
6. Configure Environments – Update the Environment variables with the credentials generated in Step 4.


## Configuration

If the above [prerequisites](#prerequisite) are fulfilled, you’ll be ready to configure the external parameters of the integration flow and the query parameter for the APIs.

| Config for:                       | Config Guide Link:                                                                                                       |
|-----------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| External parameters of the IFLOWs | [Link](https://github.com/nesun3/table-based-ui-sap-cloud-integration/blob/main/.config/External%20parameters.md#external-parmeter-configuration-on-sap-cloud-integration)|
| Query parameters of the API       | [Link](https://github.com/nesun3/table-based-ui-sap-cloud-integration/blob/main/.config/Query%20parameters.md#endpoints) |
