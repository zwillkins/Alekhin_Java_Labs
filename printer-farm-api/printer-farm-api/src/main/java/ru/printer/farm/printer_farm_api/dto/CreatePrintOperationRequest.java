package ru.printer.farm.printer_farm_api.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CreatePrintOperationRequest {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String finalStatus;
    private Integer actualWeightConsumed;
}