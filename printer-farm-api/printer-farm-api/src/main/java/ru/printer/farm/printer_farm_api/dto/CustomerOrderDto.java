package ru.printer.farm.printer_farm_api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class CustomerOrderDto {
    private Long id;
    private String customerName;
    private LocalDate dueDate;
    private String status;
    private LocalDateTime createdAt;
    private List<PrintTaskDto> tasks; 
}