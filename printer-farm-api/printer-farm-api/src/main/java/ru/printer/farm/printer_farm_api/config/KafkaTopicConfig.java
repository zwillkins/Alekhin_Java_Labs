package ru.printer.farm.printer_farm_api.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic taskCreationRequestTopic() {
        return TopicBuilder.name("task-creation-requests")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic taskCreationResponseTopic() {
        return TopicBuilder.name("task-creation-responses")
                .partitions(1)
                .replicas(1)
                .build();
    }
}