package com.example.kafka.service;

import com.example.kafka.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${topic.name}")
    private String topic;
    public ProducerService (KafkaTemplate<String,String> kafkaTemplate, ObjectMapper objectMapper){
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }
    public void sendUser(User user) throws JsonProcessingException {
        /*
        Kafka JSON gibi serileştirilmiş veri formatlarını kabul eder bu yüzden direkt olarak bir nesne gönderemeyiz.
        Bu yüzden nesne göndermek istediğimiz zaman mapper ile onu json, string gibi formatlara çevirmeliyiz.
         */
        String userMessage = objectMapper.writeValueAsString(user);
        kafkaTemplate.send(topic,userMessage);
    }
}
