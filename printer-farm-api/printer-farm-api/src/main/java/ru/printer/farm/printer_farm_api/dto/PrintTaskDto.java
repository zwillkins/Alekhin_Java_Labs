package ru.printer.farm.printer_farm_api.dto;

import lombok.Data;

@Data
public class PrintTaskDto {
    private Long id;
    private String partName;
    private Integer requiredQuantity;
    private Integer printedQuantity;
    private String status;
    private Integer priority;
    private Long orderId; 
}