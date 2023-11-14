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

def Message valueMappingUriAll(Message message) {

    def body = message.getBody(java.io.Reader)

    def prop = message.getProperties()
    List valueMappingUriAll = prop.get("valueMappingUriAll")

    Writer writer = new StringWriter()
    def indentPrinter = new IndentPrinter(writer, ' ')
    def builder = new MarkupBuilder(indentPrinter)

    builder.ValueMappings{
        valueMappingUriAll.each{ uri->
            ValueMapping{
                PackageId(uri.packageId)
                PackageName(uri.packageName)
                URI(uri.valueMappingUri)
            }
        }
    }

    message.setBody(writer.toString())
    return message
}

def Message messageMappingUriAll(Message message) {

    def body = message.getBody(java.io.Reader)

    def prop = message.getProperties()
    List messageMappingUriAll = prop.get("messageMappingUriAll")

    Writer writer = new StringWriter()
    def indentPrinter = new IndentPrinter(writer, ' ')
    def builder = new MarkupBuilder(indentPrinter)

    builder.MessageMappings{
        messageMappingUriAll.each{ uri->
            MessageMapping{
                PackageId(uri.packageId)
                PackageName(uri.packageName)
                URI(uri.messageMappingUri)
            }
        }
    }

    message.setBody(writer.toString())
    return message
}

def Message scriptCollectionUriAll(Message message) {

    def body = message.getBody(java.io.Reader)

    def prop = message.getProperties()
    List scriptCollectionUriAll = prop.get("scriptCollectionUriAll")

    Writer writer = new StringWriter()
    def indentPrinter = new IndentPrinter(writer, ' ')
    def builder = new MarkupBuilder(indentPrinter)

    builder.ScriptCollections{
        scriptCollectionUriAll.each{ uri->
            ScriptCollection{
                PackageId(uri.packageId)
                PackageName(uri.packageName)
                URI(uri.scriptCollectionUri)
            }
        }
    }

    message.setBody(writer.toString())
    return message
}

