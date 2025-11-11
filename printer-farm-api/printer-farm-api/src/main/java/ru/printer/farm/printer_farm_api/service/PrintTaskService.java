package ru.printer.farm.printer_farm_api.service;

import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.printer.farm.printer_farm_api.config.ConsoleColors;
import ru.printer.farm.printer_farm_api.dto.TaskEventDto;
import ru.printer.farm.printer_farm_api.model.CustomerOrder;
import ru.printer.farm.printer_farm_api.model.PrintTask;
import ru.printer.farm.printer_farm_api.repository.CustomerOrderRepository;
import ru.printer.farm.printer_farm_api.repository.PrintTaskRepository;

@Service
@RequiredArgsConstructor
public class PrintTaskService {
    private final PrintTaskRepository taskRepository;
    private final CustomerOrderRepository orderRepository;
    private final KafkaTemplate<String, TaskEventDto> kafkaTemplate;

    public List<PrintTask> findAll() {
        return taskRepository.findAll();
    }

    public PrintTask findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public PrintTask save(PrintTask task) {
        return taskRepository.save(task);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public PrintTask saveWithOrderId(PrintTask task, Long orderId) {
        CustomerOrder order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            return null;
        }
        task.setOrder(order);

        PrintTask savedTask = taskRepository.save(task);

        TaskEventDto event = new TaskEventDto(savedTask.getId(), savedTask.getPartName(), "CREATED");
        kafkaTemplate.send("task-creation-requests", event);
        System.out.println(ConsoleColors.PURPLE + ">>> KAFKA PRODUCER (API): Отправлено событие о создании задания с ID " + savedTask.getId() + ConsoleColors.RESET);
        
        return savedTask;
    }
}