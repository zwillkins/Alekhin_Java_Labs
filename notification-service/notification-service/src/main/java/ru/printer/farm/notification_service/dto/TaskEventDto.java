package ru.printer.farm.notification_service.dto;
import lombok.Data;

@Data
public class TaskEventDto {
    private Long taskId;
    private String partName;
    private String status;

    public TaskEventDto() {}

    public TaskEventDto(Long taskId, String partName, String status) {
        this.taskId = taskId;
        this.partName = partName;
        this.status = status;
    }

    public Long getTaskId() { return taskId; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }
    public String getPartName() { return partName; }
    public void setPartName(String partName) { this.partName = partName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "TaskEventDto{" +
                "taskId=" + taskId +
                ", partName='" + partName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}