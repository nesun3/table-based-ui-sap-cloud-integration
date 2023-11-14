import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.MarkupBuilder

def Message integrationFlowUriAll(Message message) {

    def body = message.getBody(java.io.Reader)

    def prop = message.getProperties()
    List integrationFlowUriAll = prop.get("integrationFlowUriAll")

    Writer writer = new StringWriter()
    def indentPrinter = new IndentPrinter(writer, ' ')
    def builder = new MarkupBuilder(indentPrinter)

    builder.IntegrationFlows{
        integrationFlowUriAll.each{ uri->
            IntegrationFlow{
                PackageId(uri.packageId)
                PackageName(uri.packageName)
                URI(uri.integrationFlowUri)
            }
        }
    }

    message.setBody(writer.toString())
    return message
}

def Message configurationsAll(Message message) {

    def body = message.getBody(java.io.Reader)

    Writer writer = new StringWriter()
    def indentPrinter = new IndentPrinter(writer, ' ')
    def builder = new MarkupBuilder(indentPrinter)

    def prop = message.getProperties()
    List integrationFlowAll = prop.get("integrationFlowAll")

    def integrationFlowId  = prop.get("SelectIntegrationFlowIds")
    if(integrationFlowId && integrationFlowId.toString().trim().length() != 0){

        def inputList = integrationFlowId.split(',')
        def filteredIntegrationFlows = []
        filteredIntegrationFlows = integrationFlowAll.findAll { integrationFlow -> inputList.contains(integrationFlow.integrationFlowId) }
        builder.IntegrationFlows{
            filteredIntegrationFlows.each{ iflow->
                IntegrationFlow{
                    PackageId(iflow.packageId)
                    PackageName(iflow.packageName)
                    Id(iflow.integrationFlowId)
                    Name(iflow.integrationFlowName)
                    Version(iflow.integrationFlowVersion)
                    URI(iflow.configurationsUri)
                }
            }
        }

    }
    else{

        builder.IntegrationFlows{
            integrationFlowAll.each{ iflow->
                IntegrationFlow{
                    PackageId(iflow.packageId)
                    PackageName(iflow.packageName)
                    Id(iflow.integrationFlowId)
                    Name(iflow.integrationFlowName)
                    Version(iflow.integrationFlowVersion)
                    URI(iflow.configurationsUri)
                }
            }
        }

    }

    message.setBody(writer.toString())
    return message
}

def Message resourcesAll(Message message) {

    def body = message.getBody(java.io.Reader)

    Writer writer = new StringWriter()
    def indentPrinter = new IndentPrinter(writer, ' ')
    def builder = new MarkupBuilder(indentPrinter)

    def prop = message.getProperties()
    List integrationFlowAll = prop.get("integrationFlowAll")

    def integrationFlowId  = prop.get("SelectIntegrationFlowIds")
    if(integrationFlowId && integrationFlowId.toString().trim().length() != 0){

        def inputList = integrationFlowId.split(',')
        def filteredIntegrationFlows = []
        filteredIntegrationFlows = integrationFlowAll.findAll { integrationFlow -> inputList.contains(integrationFlow.integrationFlowId) }
        builder.IntegrationFlows{
            filteredIntegrationFlows.each{ iflow->
                IntegrationFlow{
                    PackageId(iflow.packageId)
                    PackageName(iflow.packageName)
                    Id(iflow.integrationFlowId)
                    Name(iflow.integrationFlowName)
                    Version(iflow.integrationFlowVersion)
                    URI(iflow.resourcesUri)
                }
            }
        }

    }
    else{

        builder.IntegrationFlows{
            integrationFlowAll.each{ iflow->
                IntegrationFlow{
                    PackageId(iflow.packageId)
                    PackageName(iflow.packageName)
                    Id(iflow.integrationFlowId)
                    Name(iflow.integrationFlowName)
                    Version(iflow.integrationFlowVersion)
                    URI(iflow.resourcesUri)
                }
            }
        }

    }

    message.setBody(writer.toString())
    return message
}

