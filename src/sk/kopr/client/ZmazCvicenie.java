
package sk.kopr.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for zmazCvicenie complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="zmazCvicenie">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="uuidc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "zmazCvicenie", propOrder = {
    "uuidc"
})
public class ZmazCvicenie {

    protected String uuidc;

    /**
     * Gets the value of the uuidc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUuidc() {
        return uuidc;
    }

    /**
     * Sets the value of the uuidc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuidc(String value) {
        this.uuidc = value;
    }

}
