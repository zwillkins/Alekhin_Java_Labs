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
import ru.printer.farm.printer_farm_api.dto.CreatePrintTaskRequest;
import ru.printer.farm.printer_farm_api.model.PrintTask;
import ru.printer.farm.printer_farm_api.service.PrintTaskService;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Tag(name = "Print Tasks", description = "API для управления заданиями на печать")
public class PrintTaskController {
    private final PrintTaskService taskService;

    @GetMapping
    public List<PrintTask> getAllTasks() { return taskService.findAll(); }
    
    @GetMapping("/{id}")
    public ResponseEntity<PrintTask> getTaskById(@PathVariable Long id) {
        PrintTask task = taskService.findById(id);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<PrintTask> createTask(@RequestBody CreatePrintTaskRequest requestDto, @RequestParam Long orderId) {
        PrintTask task = new PrintTask();
        task.setPartName(requestDto.getPartName());
        task.setRequiredWeight(requestDto.getRequiredWeight());
        task.setRequiredTime(requestDto.getRequiredTime());
        task.setRequiredQuantity(requestDto.getRequiredQuantity());
        task.setGcodeFileName(requestDto.getGcodeFileName());
        task.setStatus(requestDto.getStatus());
        task.setPriority(requestDto.getPriority());

        PrintTask createdTask = taskService.saveWithOrderId(task, orderId);
        if (createdTask == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdTask);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PrintTask> updateTask(@PathVariable Long id, @RequestBody PrintTask taskDetails) {
        if (taskService.findById(id) == null) return ResponseEntity.notFound().build();
        taskDetails.setId(id);
        return ResponseEntity.ok(taskService.save(taskDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (taskService.findById(id) == null) return ResponseEntity.notFound().build();
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}