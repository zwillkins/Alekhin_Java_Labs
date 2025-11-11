package ru.printer.farm.printer_farm_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ru.printer.farm.printer_farm_api.dto.CreatePrintOperationRequest;
import ru.printer.farm.printer_farm_api.model.PrintOperation;
import ru.printer.farm.printer_farm_api.service.PrintOperationService;

@RestController
@RequestMapping("/api/operations")
@RequiredArgsConstructor
@Tag(name = "Print Operations", description = "API для управления операциями печати")
public class PrintOperationController {
    private final PrintOperationService operationService;

    @GetMapping
    public List<PrintOperation> getAllOperations() { return operationService.findAll(); }
    
    @GetMapping("/{id}")
    public ResponseEntity<PrintOperation> getOperationById(@PathVariable Long id) {
        PrintOperation operation = operationService.findById(id);
        return operation != null ? ResponseEntity.ok(operation) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PrintOperation> createOperation(@RequestBody CreatePrintOperationRequest requestDto, @RequestParam Long taskId) {
        PrintOperation operation = new PrintOperation();
        operation.setStartTime(requestDto.getStartTime());
        operation.setEndTime(requestDto.getEndTime());
        operation.setFinalStatus(requestDto.getFinalStatus());
        operation.setActualWeightConsumed(requestDto.getActualWeightConsumed());

        PrintOperation createdOperation = operationService.saveWithTaskId(operation, taskId);
        if (createdOperation == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdOperation);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<PrintOperation> updateOperation(@PathVariable Long id, @RequestBody PrintOperation operationDetails) {
        if (operationService.findById(id) == null) return ResponseEntity.notFound().build();
        operationDetails.setId(id);
        return ResponseEntity.ok(operationService.save(operationDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperation(@PathVariable Long id) {
        if (operationService.findById(id) == null) return ResponseEntity.notFound().build();
        operationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}