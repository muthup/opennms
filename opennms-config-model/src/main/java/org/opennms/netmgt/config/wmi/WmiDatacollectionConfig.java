/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2013-2016 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2016 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.netmgt.config.wmi;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/config/wmi-datacollection}wmi-collection" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="rrdRepository" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "wmiCollection"
})
@XmlRootElement(name = "wmi-datacollection-config")
public class WmiDatacollectionConfig {

    @XmlElement(name = "wmi-collection", required = true)
    protected List<WmiCollection> wmiCollection;
    @XmlAttribute(name = "rrdRepository", required = true)
    protected String rrdRepository;

    /**
     * Gets the value of the wmiCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wmiCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWmiCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WmiCollection }
     * 
     * 
     */
    public List<WmiCollection> getWmiCollection() {
        if (wmiCollection == null) {
            wmiCollection = new ArrayList<WmiCollection>();
        }
        return this.wmiCollection;
    }

    /**
     * Gets the value of the rrdRepository property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRrdRepository() {
        return rrdRepository;
    }

    /**
     * Sets the value of the rrdRepository property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRrdRepository(String value) {
        this.rrdRepository = value;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof WmiDatacollectionConfig)) {
            return false;
        }
        WmiDatacollectionConfig castOther = (WmiDatacollectionConfig) other;
        return Objects.equals(wmiCollection, castOther.wmiCollection)
                && Objects.equals(rrdRepository, castOther.rrdRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wmiCollection, rrdRepository);
    }

}
