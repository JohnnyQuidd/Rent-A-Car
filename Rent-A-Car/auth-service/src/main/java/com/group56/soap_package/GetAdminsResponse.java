//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.12 at 11:43:21 AM CEST 
//


package com.group56.soap_package;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="adminsXML" type="{http://group56.com/soap-package}adminXML" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "adminsXML"
})
@XmlRootElement(name = "getAdminsResponse")
public class GetAdminsResponse {

    @XmlElement(required = true)
    protected List<AdminXML> adminsXML;

    /**
     * Gets the value of the adminsXML property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the adminsXML property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdminsXML().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdminXML }
     * 
     * 
     */
    public List<AdminXML> getAdminsXML() {
        if (adminsXML == null) {
            adminsXML = new ArrayList<AdminXML>();
        }
        return this.adminsXML;
    }

    public void setAdminsXML(List<AdminXML> adminsXML) {
        if(adminsXML != null){
            this.adminsXML = adminsXML;
        }
        else {
            this.adminsXML = new ArrayList<>();
        }
    }

}
