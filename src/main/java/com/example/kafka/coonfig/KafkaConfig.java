package com.example.kafka.coonfig;

import com.example.kafka.entities.User;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Configuration
public class KafkaConfig {

    private KafkaProperties kafkaProperties;

    @Value("${topic.name}")
    private String topicName;

    public KafkaConfig(KafkaProperties kafkaProperties){
        this.kafkaProperties = kafkaProperties;
    }

    /*
    Kafka'ya mesaj göndermek için kullanılan temel yapıdır.
     */
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String,Object> properties = kafkaProperties.buildProducerProperties();
        return new DefaultKafkaProducerFactory<>(properties);
    }

    /*
    kafkatemplate, spring ile kafka arasındaki iletişimi sağlıyan yapıdır.
    producerFactory nesnesini kullanır.
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    /*
    application.properties içerisinde yer alan topic.name alanında yer alan değer isminde bir topic oluşturduk.
    topicde bir adet partition yer almaktadır.
    topic diğer broker'larda sadece 1 kere yedeklenecektir (replicas).
    Bu yedeklemenin amacı topice'e herhangi bir sorun meydana geldiğinde yedeğin kullanılarak veri kaybının önüne geçme
     */
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(topicName)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
