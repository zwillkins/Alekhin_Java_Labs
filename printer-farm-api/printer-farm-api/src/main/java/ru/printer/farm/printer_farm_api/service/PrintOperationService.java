package ru.printer.farm.printer_farm_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.printer.farm.printer_farm_api.model.PrintOperation;
import ru.printer.farm.printer_farm_api.model.PrintTask;
import ru.printer.farm.printer_farm_api.repository.PrintOperationRepository;
import ru.printer.farm.printer_farm_api.repository.PrintTaskRepository;

@Service
@RequiredArgsConstructor
public class PrintOperationService {
    private final PrintOperationRepository operationRepository;
    private final PrintTaskRepository taskRepository; // Добавляем репозиторий для заданий
    
    public List<PrintOperation> findAll() { return operationRepository.findAll(); }
    public PrintOperation findById(Long id) { return operationRepository.findById(id).orElse(null); }
    public PrintOperation save(PrintOperation operation) { return operationRepository.save(operation); }
    public void deleteById(Long id) { operationRepository.deleteById(id); }

    // НОВЫЙ МЕТОД
    public PrintOperation saveWithTaskId(PrintOperation operation, Long taskId) {
        PrintTask task = taskRepository.findById(taskId).orElse(null);
        if (task == null) {
            return null;
        }
        operation.setTask(task);
        return operationRepository.save(operation);
    }
}