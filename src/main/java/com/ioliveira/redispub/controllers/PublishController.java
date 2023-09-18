package com.ioliveira.redispub.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ioliveira.redispub.models.Person;
import com.ioliveira.redispub.publishers.MessagePublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("publish")
public class PublishController {

    private final MessagePublisher messagePublisher;

    public PublishController(final MessagePublisher messagePublisher) {
        this.messagePublisher = Objects.requireNonNull(messagePublisher);
    }

    @PostMapping("/channelPerson")
    public ResponseEntity<?> sendMessage(@RequestBody final Person person) {
        try {
            final String personAsJson = new ObjectMapper().writeValueAsString(person);
            messagePublisher.publish("channelPerson", personAsJson);
            return ResponseEntity.ok().build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
