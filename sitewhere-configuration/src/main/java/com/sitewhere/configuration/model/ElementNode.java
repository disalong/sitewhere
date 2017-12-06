/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.configuration.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Configuration node associated with an XML element.
 * 
 * @author Derek
 */
@JsonInclude(Include.NON_NULL)
public class ElementNode extends XmlNode {

    /** List of attribute nodes */
    private List<AttributeNode> attributes;

    /** Element role */
    private String role;

    /** Message shown to warn users before deleting element */
    private String onDeleteWarning;

    /** Indicates roles that require specific subtypes */
    private Map<String, String> specializes;

    /** Maps attribute group names to titles */
    private Map<String, String> attributeGroups;

    /** Indicates if the element is deprecated */
    private boolean deprecated;

    public ElementNode() {
	super(NodeType.Element);
    }

    public List<AttributeNode> getAttributes() {
	return attributes;
    }

    public void setAttributes(List<AttributeNode> attributes) {
	this.attributes = attributes;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public String getOnDeleteWarning() {
	return onDeleteWarning;
    }

    public void setOnDeleteWarning(String onDeleteWarning) {
	this.onDeleteWarning = onDeleteWarning;
    }

    public Map<String, String> getSpecializes() {
	return specializes;
    }

    public void setSpecializes(Map<String, String> specializes) {
	this.specializes = specializes;
    }

    public Map<String, String> getAttributeGroups() {
	return attributeGroups;
    }

    public void setAttributeGroups(Map<String, String> attributeGroups) {
	this.attributeGroups = attributeGroups;
    }

    public boolean isDeprecated() {
	return deprecated;
    }

    public void setDeprecated(boolean deprecated) {
	this.deprecated = deprecated;
    }

    /**
     * If an attribute is used as an index, return the name.
     * 
     * @return
     */
    public String getIndexAttribute() {
	if (attributes != null) {
	    for (AttributeNode attribute : attributes) {
		if (attribute.isIndex()) {
		    return attribute.getLocalName();
		}
	    }
	}
	return null;
    }

    /**
     * Builder for creating element nodes.
     * 
     * @author Derek
     */
    public static class Builder {

	private ElementNode element;

	public Builder(String name, String localName, String icon, IElementRoleProvider role) {
	    this.element = new ElementNode();
	    element.setName(name);
	    element.setLocalName(localName);
	    element.setIcon(icon);
	    element.setRole(role.getName());
	}

	public Builder description(String description) {
	    element.setDescription(description);
	    return this;
	}

	public Builder namespace(String namespace) {
	    element.setNamespace(namespace);
	    return this;
	}

	public Builder attribute(AttributeNode attribute) {
	    if (element.getAttributes() == null) {
		element.setAttributes(new ArrayList<AttributeNode>());
	    }
	    element.getAttributes().add(attribute);
	    return this;
	}

	public Builder specializes(IElementRoleProvider type, IElementRoleProvider subtype) {
	    if (element.getSpecializes() == null) {
		element.setSpecializes(new HashMap<String, String>());
	    }
	    element.getSpecializes().put(type.getName(), subtype.getName());
	    return this;
	}

	public Builder attributeGroup(String id, String name) {
	    if (element.getAttributeGroups() == null) {
		element.setAttributeGroups(new HashMap<String, String>());
	    }
	    element.getAttributeGroups().put(id, name);
	    return this;
	}

	public Builder warnOnDelete(String warning) {
	    element.setOnDeleteWarning(warning);
	    return this;
	}

	public Builder makeDeprecated() {
	    element.setDeprecated(true);
	    return this;
	}

	public ElementNode build() {
	    return element;
	}
    }
}