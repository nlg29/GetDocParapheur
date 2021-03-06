
package org.adullact.spring_ws.iparapheur._1;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * d
 */
@WebServiceClient(name = "InterfaceParapheurService", targetNamespace = "http://www.adullact.org/spring-ws/iparapheur/1.0", wsdlLocation = "file:./iparapheur.wsdl")
public class InterfaceParapheurService
    extends Service
{

    private final static URL INTERFACEPARAPHEURSERVICE_WSDL_LOCATION;
    private final static WebServiceException INTERFACEPARAPHEURSERVICE_EXCEPTION;
    private final static QName INTERFACEPARAPHEURSERVICE_QNAME = new QName("http://www.adullact.org/spring-ws/iparapheur/1.0", "InterfaceParapheurService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:./iparapheur.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        INTERFACEPARAPHEURSERVICE_WSDL_LOCATION = url;
        INTERFACEPARAPHEURSERVICE_EXCEPTION = e;
    }

    public InterfaceParapheurService() {
        super(__getWsdlLocation(), INTERFACEPARAPHEURSERVICE_QNAME);
    }

    public InterfaceParapheurService(WebServiceFeature... features) {
        super(__getWsdlLocation(), INTERFACEPARAPHEURSERVICE_QNAME, features);
    }

    public InterfaceParapheurService(URL wsdlLocation) {
        super(wsdlLocation, INTERFACEPARAPHEURSERVICE_QNAME);
    }

    public InterfaceParapheurService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, INTERFACEPARAPHEURSERVICE_QNAME, features);
    }

    public InterfaceParapheurService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public InterfaceParapheurService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns InterfaceParapheur
     */
    @WebEndpoint(name = "InterfaceParapheurSoap11")
    public InterfaceParapheur getInterfaceParapheurSoap11() {
        return super.getPort(new QName("http://www.adullact.org/spring-ws/iparapheur/1.0", "InterfaceParapheurSoap11"), InterfaceParapheur.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns InterfaceParapheur
     */
    @WebEndpoint(name = "InterfaceParapheurSoap11")
    public InterfaceParapheur getInterfaceParapheurSoap11(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.adullact.org/spring-ws/iparapheur/1.0", "InterfaceParapheurSoap11"), InterfaceParapheur.class, features);
    }

    private static URL __getWsdlLocation() {
        if (INTERFACEPARAPHEURSERVICE_EXCEPTION!= null) {
            throw INTERFACEPARAPHEURSERVICE_EXCEPTION;
        }
        return INTERFACEPARAPHEURSERVICE_WSDL_LOCATION;
    }

}
