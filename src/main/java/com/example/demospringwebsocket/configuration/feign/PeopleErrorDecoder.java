package com.example.demospringwebsocket.configuration.feign;

import feign.Response;
import feign.codec.ErrorDecoder;

public class PeopleErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        throw new RuntimeException("Error on integration");
    }
}
