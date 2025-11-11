// Убрали все импорты и аннотации Lombok
package ru.printer.farm.printer_farm_api.dto;
import lombok.Data; 

@Data
public class TaskEventDto {
    private Long taskId;
    private String partName;
    private String status;

    // 1. Конструктор без аргументов (обязателен для Jackson)
    public TaskEventDto() {
    }

    // 2. Конструктор со всеми полями
    public TaskEventDto(Long taskId, String partName, String status) {
        this.taskId = taskId;
        this.partName = partName;
        this.status = status;
    }

    // 3. Геттеры и Сеттеры, написанные вручную
    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}