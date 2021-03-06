/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.spi.area.request;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.sitewhere.spi.common.ILocation;

/**
 * Interface for arguments needed to create a zone.
 * 
 * @author Derek
 */
public interface IZoneCreateRequest extends Serializable {

    /**
     * Token for referencing zone.
     * 
     * @return
     */
    public String getToken();

    /**
     * Get token of parent area.
     * 
     * @return
     */
    public String getAreaToken();

    /**
     * Get zone name.
     * 
     * @return
     */
    public String getName();

    /**
     * Get zone boundary locations.
     * 
     * @return
     */
    public List<? extends ILocation> getBounds();

    /**
     * Get border color for UI.
     * 
     * @return
     */
    public String getBorderColor();

    /**
     * Get fill color for UI.
     * 
     * @return
     */
    public String getFillColor();

    /**
     * Get opacity for UI.
     * 
     * @return
     */
    public Double getOpacity();

    /**
     * Get metadata values.
     * 
     * @return
     */
    public Map<String, String> getMetadata();
}