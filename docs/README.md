This integration package [Table-Based UI for SAP Cloud Integration](https://github.com/nesun3/table-based-ui-sap-cloud-integration/blob/main/build/Table-Based%20UI%20for%20SAP%20Cloud%20Integration.zip) contains integration flows that generate an HTML page as a response, offering comprehensive insights into SAP Cloud Integration's Monitor Message Processing logs and all Integration Artifacts/Contents in the sub-account in a table-based format.

This integration package utilize the standard public OData APIs of SAP Cloud Integration under the hood.

**Key Features:**

- Provide detailed insights not only about SAP Cloud Integration’s Monitor Message Processing but also about all Integration Artifacts/Contents in the tenant in a table-based format.
- Enhanced Filtering and Search Capabilities.
- Sorting and seamlessly exporting your monitoring data as CSV, Excel, PDF, or copying it to the clipboard.

> 🔥 Most importantly, with this, our functional colleagues can now have a look at what's going on in the SAP Cloud Integration landscape without granting access to the BTP cockpit or SAP Cloud Integration sub-account. [Create separate OAuth keys with the service plan 'integration-flow' for such use cases.]

**Note:** To preview the HTML page, you will require a REST API client such as [Insomnia](https://insomnia.rest/), [Postman](https://www.postman.com/), etc.

| Integration Flow Name    | Descriptions                                                                                                                                                                                                                                                                                      |
|--------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Integration Artifacts UI | This integration flow will generate an HTML page as a response, providing detailed insights into all or specific Integration Artifacts in the sub-account in a table-based format. The endpoint for accessing this information is /http/integration-artifacts.                                    |
| Integration Assets UI    | This integration flow will generate an HTML page as a response, providing detailed insights into all or specific Integration Assets (such as External Parameter & Resources) in the sub-account in a table-based format. The endpoint for accessing this information is /http/integration-assets. |
| Message Monitoring UI    | This integration flow will generate an HTML page as a response, providing detailed insights into all or specific Monitor Message Processing logs in the sub-account in a table-based format. The endpoint for accessing this information is /http/message-logs.                                   |

📌 **You can view the user interface for each use case at the following link: 
https://nesun3.github.io/table-based-ui-examples/** 
⚠ [Please use the desktop 💻 view only, as it is not mobile responsive.]

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


# Prerequisites
Ensure that you have these [prerequisites](https://github.com/nesun3/table-based-ui-sap-cloud-integration/tree/main/.config#prerequisite) in place before proceeding with the interface configuration.

## Configuration

If the above prerequisites are fulfilled, you’ll be ready to configure the external parameters of the integration flow and the query parameter for the APIs.

| Config for:                       | Config Guide Link:                                                                                                       |
|-----------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| External parameters of the IFLOWs | [Link](https://github.com/nesun3/table-based-ui-sap-cloud-integration/blob/main/.config/External%20parameters.md#external-parmeter-configuration-on-sap-cloud-integration)|
| Query parameters of the API       | [Link](https://github.com/nesun3/table-based-ui-sap-cloud-integration/blob/main/.config/Query%20parameters.md#endpoints) |


## **You’re done! Let’s test**

**Using Insomnia:** Watch the video below:

[<img src="https://img.youtube.com/vi/N7Lso12dIjw/maxresdefault.jpg" width="60%">](https://www.youtube.com/watch?v=N7Lso12dIjw "Watch the video")

>**Know the limitations:**

>Whenever the size of the response exceeds 1MB, the Visual Preview (i.e., response window where the HTML is displayed) is unable to render the HTML Page. However, you can still view the raw HTML as a response. During testing, I observed this issue when the number of rows in the table exceeds 2000. In such cases, a workaround is to download and save the response as an *.html file. Afterward, you can drag and drop the HTML file into the browser of your choice to view tables with huge data/rows. You can also decrease the row count by using multiple filter parameters available in the query parameters. **[Note: This issue is not related with code as the same works in Postman.]**

**Using Postman:** Watch the video below:

[<img src="https://img.youtube.com/vi/RrurN0pGYSQ/maxresdefault.jpg" width="60%">](https://www.youtube.com/watch?v=RrurN0pGYSQ "Watch the video")

>**Know the limitations:**

> Since JavaScript is not enabled by default in Postman, the three export buttons (csv, excel, pdf) will not function in Visualize (i.e., the response window where the HTML is displayed). In such cases, a workaround is to download and save the response as an *.html file. Then, you can drag and drop the HTML file into the browser of your choice and utilize the export buttons as needed. **However, in Postman, there are no constraints related to the size of the response or the number of rows in the tables. During testing, it was able to render the HTML page for tables with rows count exceeding 5000**.
