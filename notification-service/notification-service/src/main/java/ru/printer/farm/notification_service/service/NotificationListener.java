package ru.printer.farm.notification_service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import ru.printer.farm.notification_service.config.ConsoleColors;
import ru.printer.farm.notification_service.dto.TaskEventDto;

@Service
public class NotificationListener {

    private final KafkaTemplate<String, TaskEventDto> kafkaTemplate;

    public NotificationListener(KafkaTemplate<String, TaskEventDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @KafkaListener(topics = "task-creation-requests", groupId = "notification-group")
    public void handleTaskCreation(TaskEventDto event) {
        System.out.println(
            ConsoleColors.YELLOW + "<<< KAFKA CONSUMER (Notifier): Получено событие для задания ID " + event.getTaskId() +
            " ('" + event.getPartName() + "')." + ConsoleColors.RESET
        );

        System.out.println(ConsoleColors.YELLOW + "...Имитация отправки уведомления..." + ConsoleColors.RESET);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        event.setStatus("NOTIFIED_BY_SERVICE");
        
        kafkaTemplate.send("task-creation-responses", event);
        System.out.println(
            ConsoleColors.GREEN + ">>> KAFKA PRODUCER (Notifier): Отправлен ответ для задания ID " + event.getTaskId() +
            ". Новый статус '" + event.getStatus() + "'." + ConsoleColors.RESET
        );
    }
}