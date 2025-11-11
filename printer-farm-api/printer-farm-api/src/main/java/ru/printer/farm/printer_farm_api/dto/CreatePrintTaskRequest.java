package ru.printer.farm.printer_farm_api.dto;

import lombok.Data;


@Data
public class CreatePrintTaskRequest {
    private String partName;
    private Integer requiredWeight;
    private Integer requiredTime;
    private Integer requiredQuantity;
    private String gcodeFileName;
    private String status;
    private Integer priority;
}