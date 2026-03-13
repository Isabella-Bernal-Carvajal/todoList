package com.example.todolist.dto;

import java.time.LocalDateTime;

import com.example.todolist.domain.Priority;
import com.example.todolist.domain.Status;

import lombok.Data;

@Data

public class TaskResponseDTO {
    private Long id;

    private String title;

    private String description;

    private Status status;

    private Priority priority;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}