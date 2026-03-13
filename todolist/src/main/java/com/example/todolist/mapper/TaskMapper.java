package com.example.todolist.mapper;

import org.springframework.stereotype.Component;
import com.example.todolist.dto.TaskResponseDTO;
import com.example.todolist.entity.Task;

@Component

public class TaskMapper {
    
    public TaskResponseDTO mapToResponse(Task task){
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setCreatedAt(task.getCreatedAt());
        dto.setUpdatedAt(task.getUpdatedAt());

        return dto;
    }
}
