This integration package [Table-Based UI for SAP Cloud Integration](https://github.com/nesun3/table-based-ui-sap-cloud-integration/blob/main/build/Table-Based%20UI%20for%20SAP%20Cloud%20Integration.zip) contains integration flows that generate an HTML page as a response, offering comprehensive insights into SAP Cloud Integration's Monitor Message Processing logs and all Integration Artifacts/Contents in the sub-account in a table-based format.

**Key Features:**

- Provide detailed insights not only about SAP Cloud Integration’s Monitor Message Processing but also about all Integration Artifacts/Contents in the tenant in a table-based format.
- Enhanced Filtering and Search Capabilities.
- Sorting and seamlessly exporting your monitoring data as CSV, Excel, PDF, or copying it to the clipboard.
>**Note:** To preview the HTML page, you will require a REST API client such as [Insomnia](https://insomnia.rest/), [Postman](https://www.postman.com/), etc.

| Integration Flow Name    | Descriptions                                                                                                                                                                                                                                                                                      |
|--------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Integration Artifacts UI | This integration flow will generate an HTML page as a response, providing detailed insights into all or specific Integration Artifacts in the sub-account in a table-based format. The endpoint for accessing this information is /http/integration-artifacts.                                    |
| Integration Assets UI    | This integration flow will generate an HTML page as a response, providing detailed insights into all or specific Integration Assets (such as External Parameter & Resources) in the sub-account in a table-based format. The endpoint for accessing this information is /http/integration-assets. |
| Message Monitoring UI    | This integration flow will generate an HTML page as a response, providing detailed insights into all or specific Monitor Message Processing logs in the sub-account in a table-based format. The endpoint for accessing this information is /http/message-logs.                                   |


**Some Key Points:**

1. Since the output is an API response in HTML, you will need a REST API client like Insomnia by Kong, Postman, etc.
1. The solution has been tested specifically with the REST API clients Insomnia by Kong and Postman. Both have some limitations and corresponding workarounds, which will be discussed later.
1. While it is recommended to use either Insomnia by Kong or Postman as your REST API client, it is not mandatory. You can opt for a REST API client of your preference; however, ensure that it supports the preview/rendering of HTML pages. For instance, the Advanced Rest Client provides responses in HTML but lacks a preview option. In such cases, a workaround is to download and save the response as an *.html file, then drag and drop the HTML file into the browser of your choice.


The mentioned integration flow will generate an HTML page as a response. To preview the HTML page using Postman or Insomnia by Kong, please refer to the corresponding collection name in the table below. [Download](https://github.com/nesun3/table-based-ui-sap-cloud-integration/tree/main/build) the Postman or Insomnia collection.

| Integration Flow Name    | Collection Name        | HTTP Request Name               | HTTP Method | Endpoint                    |
|--------------------------|------------------------|---------------------------------|-------------|-----------------------------|
| Integration Artifacts UI | Integration Content UI | Get all Integration Artifacts   | GET         | /http/integration-artifacts |
| Integration Assets UI    | Integration Content UI | Get all Integration Assets      | GET         | /http/integration-assets    |
| Message Monitoring UI    | Message Monitoring UI  | Get all Message Processing Logs | GET         | /http/message-logs          |
