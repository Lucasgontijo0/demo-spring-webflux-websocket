package com.example.demospringwebsocket.resource;

import com.example.demospringwebsocket.People;
import com.example.demospringwebsocket.configuration.feign.PeopleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MyWebSocketHandler implements WebSocketHandler {

    private Logger log = LoggerFactory.getLogger(getClass());

    private ObjectMapper mapper;
    private final PeopleService peopleService;

    public MyWebSocketHandler(PeopleService peopleService) {
        this.peopleService = peopleService;
        this.mapper = new ObjectMapper();
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {

        log.info("Opened socket with id: { " + session.getId() + " }");

        Flux<WebSocketMessage> output = session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .map(this::findPeople)
                .map(value -> session.textMessage(value.toString()));

        final Mono<Void> response = session.send(output);

        log.info("Completed processing request in socket with id: { " + session.getId() + " }");

        return response;
    }

    private People findPeople(String input) {
        Long id = Long.parseLong(input);
        return this.peopleService.getPeopleById(id);
    }

    private String toJSON(People people) {
        try {
            return mapper.writeValueAsString(people);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
