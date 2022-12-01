package com.globant.communication.requests.factory;

import com.globant.communication.requests.Request;

public interface RequestFactory<T> {
    Request<T> generateRequest();
}
