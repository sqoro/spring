
package com.apospiech.springhttpclient.api.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class USD implements Serializable
{

    public Double price;
    public Double volume24h;
    public Double percentChange1h;
    public Double percentChange24h;
    public Double percentChange7d;
    public Double percentChange30d;
    public Double marketCap;
    public Double lastUpdated;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 4030494636626554610L;

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
