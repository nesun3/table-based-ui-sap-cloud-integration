<h1 align="center">Table-based User Interfaces (UI)</h1>
<p align="center">
User-friendly Table-based User Interfaces (UI) within the context of SAP Cloud Integration.
</p>
<div align="center">
  
[![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
![GitHub](https://img.shields.io/badge/sap_integration-Custom-blue)
</div>

## About this tool

As an SAP PO developer, you’re no stranger to the importance of message monitoring. It’s your go-to tool for understanding what’s happening in your SAP landscape. If you’ve had experience with SAP Process Orchestration (PO), you will be familiar with the Message Status Overview, which serves as your gateway to message monitoring. This invaluable tool simplifies the process of searching for messages and identifying errors. However, when you transition to SAP Cloud Integration (CPI), you might find yourself missing the simplicity and convenience of SAP PO’s Message Monitoring Overview. SAP PO Message monitoring overview and its equivalent is not present currently on SAP Cloud Integration / CPI. The look and feel and the navigation process is quite different as it built on top of SAP UI5.

To bridge the gap between SAP PO’s Message Monitoring Overview and the SAP Cloud Integration Monitor Message Processing and to provide a monitoring experience comparable to the familiar SAP PO Message Monitoring Overview, I have created a user-friendly solution called the **Table-Based UI**. This solution is crafted to provide you with all the necessary information to facilitate your daily tasks and simplify message monitoring, much like the experience in SAP PO. It is entirely designed using the fundamental building blocks and artifacts of SAP Cloud Integration, harnessing the inherent capabilities of the platform, and utilizing the power of the Groovy Markup Builder. This approach ensures that the solution aligns more closely with the platform’s core development style and pushing the boundaries of what can be accomplished within this framework.

Additionally, I have broadened this approach to include Integration Artifacts and Content, making it your go-to tool for gaining comprehensive insights into all integration contents within your sub-account.

**Here’s a sneak-peak to the solution:** Watch the video below:

[<img src="https://img.youtube.com/vi/l6lwwu7u3cU/maxresdefault.jpg" width="60%">](https://www.youtube.com/watch?v=l6lwwu7u3cU "Watch the video")

A write-up about this tool can be found here:

📌 **You can view the user interface for each use case at the following link: 
https://nesun3.github.io/table-based-ui-examples/** 
⚠ [Please use the desktop 💻 view only, as it is not mobile responsive.]

## Requirements
 - You will need a SAP Cloud Integration tenant to consume, test and enhance this integrationartifacts.
 - **To preview the HTML page, you will need a REST API client such as Insomnia, Postman, etc**.

## Download and Installation
This repository contains
1. [Integration Package](https://github.com/nesun3/table-based-ui-sap-cloud-integration/tree/main/build) - Download the Integration package named `Table-Based UI for SAP Cloud Integration.zip` and import it to your tenant.
2. [Source Code](https://github.com/nesun3/table-based-ui-sap-cloud-integration/tree/main/src) - The unzip or raw file contents of each integration flows.
3. Based on the REST API client of your preference, download the corresponding Zip file. Unzip and import the collections and environment.
   - [Postman Collection](https://github.com/nesun3/table-based-ui-sap-cloud-integration/tree/main/build)
   - [Insomnia Collection](https://github.com/nesun3/table-based-ui-sap-cloud-integration/tree/main/build)

## Documentation
To learn how to use the integration artifacts, please refer to the [user documentation](https://github.com/nesun3/table-based-ui-sap-cloud-integration/tree/main/docs).

📌 **You can view the user interface for each use case at the following link: 
https://nesun3.github.io/table-based-ui-examples/** ⚠ [Please use the desktop 💻 view only, as it is not mobile responsive.]

## Features

  - Provide detailed insights not only about SAP Cloud Integration’s Monitor Message Processing but also about all Integration Artifacts/Contents in the tenant in a table-based format.
  - Enhanced Filtering and Search Capabilities.
  - Sorting and seamlessly exporting your monitoring data as CSV, Excel, PDF, or copying it to the clipboard.
  

    
## Support, Feedback, Contributing

This project is open to feature requests/suggestions, bug reports, etc. via [GitHub issues](https://github.com/nesun3/table-based-ui-sap-cloud-integration/issues/new/choose).

>This project is currently not accepting any contribution, but may change in future.


## License
See the [LICENSE](LICENSE) file for details


## 🌱 Support the Project

>**Encourage this repo by giving it a star. If you like this concept, click on 'Star' [on the top right of the screen]. This is the GitHub equivalent of '👍 ' or 'like' or '+1'**.

*If you've found my work helpful in your project, please consider buying me a coffee to show your support!*

<a href="https://www.buymeacoffee.com/nesun3" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/v2/default-yellow.png" alt="Buy Me A Coffee" style="height: 60px !important;width: 217px !important;" ></a>


<hr>
<p align="center">
Developed with ❤️ in India 🇮🇳 
</p>
