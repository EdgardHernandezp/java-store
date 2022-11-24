package com.globant.pojos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteInfo {
    private Integer resourceType;
    private Integer resourceId;

    @JsonCreator
    public DeleteInfo(@JsonProperty("resourceType") Integer resourceType, @JsonProperty("resourceId") Integer resourceId) {
        this.resourceType = resourceType;
        this.resourceId = resourceId;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public Integer getResourceId() {
        return resourceId;
    }
}
