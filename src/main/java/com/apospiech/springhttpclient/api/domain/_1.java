
package com.apospiech.springhttpclient.api.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _1 implements Serializable
{

    public Integer id;
    public String name;
    public String symbol;
    public String slug;
    public Integer numMarketPairs;
    public String dateAdded;
    public List<String> tags = null;
    public Integer maxSupply;
    public Integer circulatingSupply;
    public Integer totalSupply;
    public Integer isActive;
    public Object platform;
    public Integer cmcRank;
    public Integer isFiat;
    public String lastUpdated;
    public Quote quote;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 1870494178914712539L;

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
