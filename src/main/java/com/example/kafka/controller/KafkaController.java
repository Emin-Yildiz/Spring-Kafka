package com.example.kafka.controller;

import com.example.kafka.entities.User;
import com.example.kafka.service.ProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaController {

    private final ProducerService producerService;

    public KafkaController(ProducerService producerService){
        this.producerService = producerService;
    }

    @PostMapping()
    public void postValue(@RequestBody User newUser) throws JsonProcessingException {
        producerService.sendUser(newUser);
        log.info("Sending user");
    }

}
