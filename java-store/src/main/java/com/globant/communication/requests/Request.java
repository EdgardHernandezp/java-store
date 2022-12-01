package com.globant.communication.requests;

import com.globant.communication.Actions;

public class Request<T> {
    private final Actions action;
    private final T body;

    public Request(Actions action, T body) {
        this.action = action;
        this.body = body;
    }

    public Actions getAction() {
        return action;
    }

    public T getBody() {
        return body;
    }
}
