package com.globant.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.globant.pojos.DeleteInfo;

@JsonTypeName("deleteProduct")
public class DeleteStoreRequest extends StoreRequest<DeleteInfo> {
    @JsonCreator
    public DeleteStoreRequest(@JsonProperty("action") String action,@JsonProperty("body") DeleteInfo body) {
        super(action, body);
    }
}