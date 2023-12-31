
## Endpoints
- [/http/integration-artifacts](#get-all-integration-artifacts)
- [/http/integration-assets](#get-all-integration-assets)
- [/http/message-logs](#get-all-message-processing-logs)

## Get all Integration Artifacts

**`GET {{host}}/http/integration-artifacts`**

Returns an HTML as a response, providing detailed insights into all or specific Integration Artifacts in the sub-account in a table-based format.
>The endpoint requires OAuth 2.0 (Grant type - Client Credentials) Authorization.

>Query Parameters Name's are case-sentive

**Query Parameters**

| Name                        | Required | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 | Example                           |
|-----------------------------|----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------|
| GroupBy                     | No       | Group Integration Artifact by count. <br>**Allowed Values:** count. [can accept values in any case (upper, lower, or mixed)]                                                                                                                                                                                                                                                                                                                                                                                                                                                | `count`                           |
| SelectArtifactType          | **Yes**  | Select Integration Artifact Type. This will filter Integration Artifact Type selected. <br>**Allowed Values:** all,ifl,vmp,mmc,src. [can accept values in any case (upper, lower, or mixed)]. <br>**Note:** <br>*all*- To select all artifacts type (*iflows, value mappings, script collections, message mappings*).<br> *ifl* - To select only artifacts type (*iflows*). <br>*vmp* - To select only artifacts type (*value mappings*). <br>*src* - To select only artifacts type (*script collections*). <br>*mmc* - To select only artifacts type (*message mappings*). | `all` <br> `ifl,vmp`              |
| SelectIntegrationPackageIds | No       | Integration Package **IDs** to be included in the extract (comma-separated values). This will filter only those specific Integration Package IDs.                                                                                                                                                                                                                                                                                                                                                                                                                           | `TestPackage01,TestPackage02`     |
| SelectIntegrationFlowIds    | No       | Integration flow **IDs** to be included in the extract (comma-separated values). This will filter only those specific Integration flow IDs.                                                                                                                                                                                                                                                                                                                                                                                                                                 | `Test 02,Test 05`                 |
| SelectValueMappingIds       | No       | Value Mapping **IDs** to be included in the extract (comma-separated values). This will filter only those specific Value Mapping IDs.                                                                                                                                                                                                                                                                                                                                                                                                                                       | `Name_Suffix,Pay_Frequency`       |
| SelectScriptCollectionIds   | No       | Script Collection **IDs** to be included in the extract (comma-separated values). This will filter only those specific Script Collection IDs.                                                                                                                                                                                                                                                                                                                                                                                                                               | `SrcCollection01,SrcCollection02` |
| SelectMessageMappingIds     | No       | Message Mapping **IDs** to be included in the extract (comma-separated values). This will filter only those specific Message Mapping IDs                                                                                                                                                                                                                                                                                                                                                                                                                                    | `MMAP_01,MMAP_02`                 |
| Top                         | No       | Show only the first n packages.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                | `50`                              |
| Skip                        | No       | Skip the first n packages.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     | `100`                             |

**Status codes**

| Status code | Description |
|-----------------|-----------------------------------------------------|
| 200 OK | Indicates a successful response. |
| 412 Precondition Failed | Indicates that either the mandatory parameter is missing or the parameters provided are invalid. |


## Get all Integration Assets

**`GET {{host}}/http/integration-assets`**

Returns an HTML as a response, providing detailed insights into all or specific Integration Assets (such as External Parameter & Resources) in the sub-account in a table-based format.
>The endpoint requires OAuth 2.0 (Grant type - Client Credentials) Authorization.

>Query Parameters Name's are case-sentive

**Query Parameters**

| Name                        | Required | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 | Example                           |
|-----------------------------|----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------|
| FilterBy                    | **Yes**  | Select get configuration or resources of all integration flows. <br>**Allowed Values:** conf or res. [can accept values in any case (upper, lower, or mixed)]  <br>**Note:** <br>conf - External Paramters/ Configurations <br> res - resources                                                                                                                                                                                                                                                                                                                                 | `conf`<br> `res`                  |
| SelectIntegrationPackageIds | No       | Integration Package **IDs** to be included in the extract (comma-separated values). This will filter only those specific Integration Package IDs.                                                                                                                                                                                                                                                                                                                                                                                                                           | `TestPackage01,TestPackage02`     |
| SelectIntegrationFlowIds    | No       | Integration flow **IDs** to be included in the extract (comma-separated values). This will filter only those specific Integration flow IDs.                                                                                                                                                                                                                                                                                                                                                                                                                                 | `Test 02,Test 05`                 |
| Top                         | No       | Show only the first n packages.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                | `50`                              |
| Skip                        | No       | Skip the first n packages.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     | `100`                             |

**Status codes**

| Status code | Description |
|-----------------|-----------------------------------------------------|
| 200 OK | Indicates a successful response. |
| 412 Precondition Failed | Indicates that either the mandatory parameter is missing or the parameters provided are invalid. |



## Get all Message Processing Logs

**`GET {{host}}/http/message-logs`**

Returns an HTML as a response, providing detailed insights into all or specific Monitor Message Processing logs in the sub-account in a table-based format.
>The endpoint requires OAuth 2.0 (Grant type - Client Credentials) Authorization.

>Query Parameters Name's are case-sentive

**Some Key Points**
 - If query params FromDateTime and ToDateTime are not provided, the API response defaults to the past 24 hours as the date range.
 - The API response filters all messages having status equal to "discarded" by default.
 - The API accepts a maximum date range of 90 days.

**Query Parameters**

| Name                        | Required | Description                                                                                                                                              | Example                       |
|-----------------------------|----------|----------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------|
| GroupBy                     | No       | Group logs by Message Status. <br>**Allowed Values:** status. [can accept values in any case (upper, lower, or mixed)]                                   | `status`                      |
| FromDateTime                | No       | From Date Time in UTC.                                                                                                                                   | `2023-11-01T00:00:00Z`        |
| ToDateTime                  | No       | To Date Time in UTC.                                                                                                                                     | `2023-11-11T00:00:00Z`        |
| Status                      | No       | Filter by Status.  <br>**Allowed values:** completed, failed, escalated, cancelled, abandoned. [can accept values in any case (upper, lower, or mixed)]. | `Failed`                      |
| SelectIntegrationPackageIds | No       | Integration Package **IDs** to be included in the extract (comma-separated values). This will filter only those specific Integration Package IDs.        | `TestPackage01,TestPackage02` |
| SelectIntegrationFlowIds    | No       | Integration flow **IDs** to be included in the extract (comma-separated values). This will filter only those specific Integration flow IDs.              | `Test 02,Test 05`             |
| Top                         | No       | Show only the first n packages.                                                                                                                          | `50`                          |
| Skip                        | No       | Skip the first n packages.                                                                                                                               | `100`                         |


**Status codes**

| Status code | Description |
|-----------------|-----------------------------------------------------|
| 200 OK | Indicates a successful response. |
| 412 Precondition Failed | Indicates that either the mandatory parameter is missing or the parameters provided are invalid. |

