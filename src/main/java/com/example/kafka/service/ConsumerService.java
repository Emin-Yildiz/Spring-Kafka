package com.example.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

//    @Value("${topic.name}")
//    private String topicName;

    // Aşağıdaki topics parametresine sabit bir değer girilmesi gerektiğinden dolayı application.properties klasöründeki
    // topic.name alanını ordan çekemiyoruz.
    @KafkaListener(topics = "deneme-topic", groupId = "group-id")
    public void getMessage(String message){
      log.info(message);
    }
}
