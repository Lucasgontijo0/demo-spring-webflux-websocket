package com.example.demospringwebsocket.configuration.feign;

import com.example.demospringwebsocket.People;
import feign.Param;
import feign.RequestLine;

public interface PeopleApi {

    @RequestLine("GET /people/{peopleId}")
    People getPeople(@Param("peopleId") Long id);
}
