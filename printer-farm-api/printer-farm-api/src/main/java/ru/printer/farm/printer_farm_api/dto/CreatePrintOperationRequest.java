package ru.printer.farm.printer_farm_api.dto;

import lombok.Data;
import java.time.LocalDateTime;

// DTO для запроса на создание PrintOperation.
@Data
public class CreatePrintOperationRequest {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String finalStatus;
    private Integer actualWeightConsumed;
}