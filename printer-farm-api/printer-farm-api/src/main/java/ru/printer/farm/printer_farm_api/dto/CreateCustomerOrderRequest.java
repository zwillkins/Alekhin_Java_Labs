package ru.printer.farm.printer_farm_api.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CreateCustomerOrderRequest {
    private String customerName;
    private LocalDate dueDate;
    private String status;
}