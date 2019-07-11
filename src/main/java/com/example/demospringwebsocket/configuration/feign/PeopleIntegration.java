package com.example.demospringwebsocket.configuration.feign;

import com.example.demospringwebsocket.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
public class PeopleIntegration implements PeopleService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final PeopleApi peopleApi;

    public PeopleIntegration(PeopleApi peopleApi) {
        this.peopleApi = peopleApi;
    }

    @Override
    public People getPeopleById(Long id) {

        StopWatch watch = new StopWatch();
        watch.start();
        final People people = this.peopleApi.getPeople(id);
        watch.stop();
        log.warn("Integration response: " + people + " Time elapsed:" + watch.getTotalTimeMillis());
        return people;
    }
}
