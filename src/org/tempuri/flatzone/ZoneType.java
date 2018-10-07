//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.07 at 06:25:40 PM MSK 
//


package org.tempuri.flatzone;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for ZoneType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZoneType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="4">
 *         &lt;element name="Triangle" type="{http://tempuri.org/FlatZone.xsd}TriangleType"/>
 *         &lt;element name="Circle" type="{http://tempuri.org/FlatZone.xsd}CircleType"/>
 *         &lt;element name="Rectangle" type="{http://tempuri.org/FlatZone.xsd}RectangleType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZoneType", propOrder = {
    "triangleOrCircleOrRectangle"
})
public class ZoneType {

    @XmlElements({
        @XmlElement(name = "Triangle", type = TriangleType.class),
        @XmlElement(name = "Circle", type = CircleType.class),
        @XmlElement(name = "Rectangle", type = RectangleType.class)
    })
    protected List<ShapeType> triangleOrCircleOrRectangle;

    /**
     * Gets the value of the triangleOrCircleOrRectangle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the triangleOrCircleOrRectangle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTriangleOrCircleOrRectangle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TriangleType }
     * {@link CircleType }
     * {@link RectangleType }
     * 
     * 
     */
    public List<ShapeType> getTriangleOrCircleOrRectangle() {
        if (triangleOrCircleOrRectangle == null) {
            triangleOrCircleOrRectangle = new ArrayList<ShapeType>();
        }
        return this.triangleOrCircleOrRectangle;
    }

}
