package com.globant.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.globant.Actions;
import com.globant.pojos.DeleteInfo;

public class DeleteStoreRequest extends StoreRequest<DeleteInfo> {
    @JsonCreator
    public DeleteStoreRequest(@JsonProperty("action") Actions action,@JsonProperty("body") DeleteInfo body) {
        super(action, body);
    }
}
