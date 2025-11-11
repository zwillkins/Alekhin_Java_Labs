package ru.printer.farm.printer_farm_api.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import ru.printer.farm.printer_farm_api.config.ConsoleColors;
import ru.printer.farm.printer_farm_api.dto.TaskEventDto;

@Service
public class ResponseListener {

    // Этот метод будет "слушать" топик с ответами
    @KafkaListener(topics = "task-creation-responses", groupId = "printer-farm-group")
    public void handleTaskResponse(TaskEventDto event) {
         // --- ИСПОЛЬЗУЕМ ЦВЕТА ---
        System.out.println(
            ConsoleColors.CYAN + "<<< KAFKA CONSUMER (API): Получен ответ! Статус задания ID " + event.getTaskId() +
            " обновлен на '" + event.getStatus() + "'." + ConsoleColors.RESET
        );
        // -----------------------
    }
}