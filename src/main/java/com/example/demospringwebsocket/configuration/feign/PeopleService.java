package com.example.demospringwebsocket.configuration.feign;

import com.example.demospringwebsocket.People;

public interface PeopleService {

    People getPeopleById(Long id);
}
