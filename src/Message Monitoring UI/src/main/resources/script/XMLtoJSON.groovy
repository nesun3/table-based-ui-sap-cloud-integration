import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import java.text.SimpleDateFormat

def Message processData(Message message) {
    def body = message.getBody(java.io.Reader)
    def xmlData = new XmlSlurper().parse(body)

    def builder = new JsonBuilder()
    builder {
        d {
            results(xmlData.MessageProcessingLog.collect { log ->
                [
                        MessageGuid: log.MessageGuid.text(),
                        CorrelationId: log.CorrelationId.text(),
                        LogStart: parseDate(log.LogStart.text()),
                        LogEnd: parseDate(log.LogEnd.text()),
                        IntegrationFlowName: log.IntegrationFlowName.text(),
                        Status: log.Status.text(),
                        AlternateWebLink: log.AlternateWebLink.text(),
                        IntegrationArtifact: [
                                Id: log.IntegrationArtifact.Id.text(),
                                Name: log.IntegrationArtifact.Name.text(),
                                Type: log.IntegrationArtifact.Type.text(),
                                PackageId: log.IntegrationArtifact.PackageId.text(),
                                PackageName: log.IntegrationArtifact.PackageName.text()
                        ]
                ]
            })
        }
    }

    //println builder.toPrettyString()
    message.setBody(builder.toString())
    return message
}

def parseDate(dateStr) {
    def date = Date.parse("yyyy-MM-dd'T'HH:mm:ss.SSS", dateStr)
    return date.format("yyyy-MM-dd HH:mm:ss:SSS")
}
