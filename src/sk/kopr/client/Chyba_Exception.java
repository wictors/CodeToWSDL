
package sk.kopr.client;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "Chyba", targetNamespace = "http://server.kopr.sk/")
public class Chyba_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private Chyba faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public Chyba_Exception(String message, Chyba faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public Chyba_Exception(String message, Chyba faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: sk.kopr.client.Chyba
     */
    public Chyba getFaultInfo() {
        return faultInfo;
    }

}
