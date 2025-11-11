package ru.printer.farm.printer_farm_api.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// Этот класс используется для передачи данных через API. Он не привязан к базе данных.
@Data
public class CustomerOrderDto {
    private Long id;
    private String customerName;
    private LocalDate dueDate;
    private String status;
    private LocalDateTime createdAt;
    private List<PrintTaskDto> tasks; // Используем DTO для вложенных объектов
}