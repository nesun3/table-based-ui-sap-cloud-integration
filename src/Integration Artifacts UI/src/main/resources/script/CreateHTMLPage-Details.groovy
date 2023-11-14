import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.MarkupBuilder

def Message processData(Message message) {

    def prop = message.getProperties()
    def pageTitle = prop.get("pageTitle")

    def date = new Date()
    def dateFormatter = new java.text.SimpleDateFormat("MMM dd, yyyy, HH:mm:ss")
    dateFormatter.timeZone = TimeZone.getTimeZone("UTC")
    def formattedDate = dateFormatter.format(date)

    def listOfMaps
    def groupBy = prop.get("GroupBy")
    if (groupBy.toString().toUpperCase() == "COUNT") {
        List packageAll = prop.get("packageAll")
        listOfMaps = packageAll
    } else {
        List integrationArtifactsAll = prop.get("integrationArtifactsAll")
        listOfMaps = integrationArtifactsAll
    }

    def writer = new StringWriter()
    def index = new MarkupBuilder(writer)

    // Ensure the listOfMaps is not empty
    if (listOfMaps) {
        def keys = listOfMaps[0].keySet() as List<String>
        println keys

        def doctype = '<!DOCTYPE html>'
        // Manually insert the DOCTYPE declaration
        writer << doctype
        // Generate the HTML using MarkupBuilder
        index.html(lang: "en") {
            head {
                meta(charset: "UTF-8")
                meta(name: "viewport", content: "width=device-width, initial-scale=1.0")
                title("Table-Based UI for SAP Cloud Integration")
                /* Basic Style */
//                link(rel: "stylesheet", type: "text/css", href: "https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css")
                /* Bootstrap3 */
                link(rel: "stylesheet", type: "text/css", href: "https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap.min.css")
                link(rel: "stylesheet", type: "text/css", href: "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css")
                /* Bootstrap3 */
                link(rel: "stylesheet", type: "text/css", href: "https://cdn.datatables.net/buttons/2.4.2/css/buttons.dataTables.min.css")
                link(rel: "stylesheet", type: "text/css", href: "https://cdn.datatables.net/fixedheader/3.4.0/css/fixedHeader.dataTables.min.css")
                link(rel: "stylesheet", type: "text/css", href: "https://cdn.datatables.net/fixedcolumns/4.3.0/css/fixedColumns.dataTables.min.css")
                link(rel: "stylesheet", type: "text/css", href: "https://cdn.datatables.net/select/1.7.0/css/select.dataTables.min.css")
                style {
                    mkp.yieldUnescaped("""
                    body {background-color: #fafafa;}
                    h2 {text-align: center;padding: 20px 0;}
                    thead {background-color: #72b5f8;}
                    caption {text-align: left;padding: 1rem;}
                    @keyframes colorChange {
                    0% {color: #DF8453;}
                    25% {color: #3d8dae;}
                    50% {color: #e4a9a8;}
                    75% {color: #dbad4a;}
                    100% {color: #17494d;}
                    }
                    p {text-align: center;animation: colorChange 10s linear infinite;}
                    """)
                }
                script(src: "https://code.jquery.com/jquery-3.7.0.js", "")
                script(src: "https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js", "")
                /* Bootstrap3 */
                script(src: "https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap.min.js", "")
                /* Bootstrap3 */
                script(src: "https://cdn.datatables.net/buttons/2.4.2/js/dataTables.buttons.min.js", "")
                script(src: "https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js", "")
                script(src: "https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js", "")
                script(src: "https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js", "")
                script(src: "https://cdn.datatables.net/buttons/2.4.2/js/buttons.html5.min.js", "")
                script(src: "https://cdn.datatables.net/buttons/2.4.2/js/buttons.colVis.min.js", "")
                script(src: "https://cdn.datatables.net/fixedheader/3.4.0/js/dataTables.fixedHeader.min.js", "")
                script(src: "https://cdn.datatables.net/fixedcolumns/4.3.0/js/dataTables.fixedColumns.min.js", "")
                script(src: "https://cdn.datatables.net/select/1.7.0/js/dataTables.select.min.js", "")
                script(src: "https://kit.fontawesome.com/04cf6b748f.js",crossorigin: "anonymous", "")
            }
            body {
                div(class: "table-container", "") {
                    div(class: "table-info", "") {
                        h2("$pageTitle")
                    }
                    div(class: "blank-row", "")
                    table(id: "example", "class": "table table-striped table-bordered", style: "width:100%") {
                        caption("As of date: $formattedDate UTC")
                        thead(class: "table-header-row") {
                            tr {
                                keys.each { key ->
                                    th(key)
                                }
                            }
                        }
                        tbody {
                            listOfMaps.each { map ->
                                tr {
                                    keys.each { key ->
                                        td(map[key])
                                    }
                                }
                            }
                        }
                        tfoot {
                            tr {
                                keys.each { key ->
                                    th(key)
                                }
                            }
                        }
                    }
                }
                div(class: "separate", mkp.yieldUnescaped("<hr>"))
                div(class: "footer", ""){
                    p {
                        span("Coded with")
                        span{
                            i(class: "fa-solid fa-heart fa-flip",style: "color: #e91e63", "")
                        }
                        span("by Surajit Neogi. Follow me on")
                        span{
                            a(target: '_blank', href: "https://github.com/nesun3?tab=repositories") {
                                i(class: "fa-brands fa-github fa-bounce fa-lg", "")
                            }
                        }
                    }
                }
                script {
                    mkp.yieldUnescaped("""
                \$(document).ready(function() {
                     document.title = "All Integration Artifacts Details " + new Date().toISOString().slice(0, 19).replaceAll(':','');
                    \$('#example').DataTable( { 
                        dom: 'Bifrtlp',
                        fixedHeader: true,
                        fixedColumns:{
                            left: 3
                        },
                        buttons: [  
                            { extend: 'copy', title: '', exportOptions: { columns: ':visible', modifier: {selected: null}} },
                            { extend: 'csv', title: '', exportOptions: { columns: ':visible', modifier: {selected: null}} },
                            { extend: 'excel', title: '', exportOptions: { columns: ':visible', modifier: {selected: null}} },
                            { extend: 'pdf', title: '', exportOptions: { columns: ':visible', modifier: {selected: null}}, orientation: 'landscape', pageSize: 'LEGAL' },
                            'colvis'
                        ],
                         columnDefs: [ 
                             { visible: false, targets: [-1,-2] }
                        ],
                        select: true,
                        initComplete: function () {
                          this.api()
                            .columns()
                            .every(function () {
                               let column = this;
                               let title = column.footer().textContent;

                               // Create input element
                               let input = document.createElement("input");
                               input.placeholder = title;
                               column.footer().replaceChildren(input);

                               // Event listener for user input
                               input.addEventListener("keyup", () => {
                                 if (column.search() !== this.value) {
                                   column.search(input.value).draw();
                                 }
                               });
                            });
                        }
                    });
                });
                """)
                }
            }
        }

        // println writer
        message.setBody(writer)
    } else
        message.setBody("Detailed Integration Artifacts returns no result")

    return message
}