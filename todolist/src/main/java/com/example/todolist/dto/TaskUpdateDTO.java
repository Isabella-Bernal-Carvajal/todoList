package com.example.todolist.dto;

import com.example.todolist.domain.Priority;
import com.example.todolist.domain.Status;

import lombok.Data;

@Data

public class TaskUpdateDTO {
    private String title;
    private String description;
    private Status status;
    private Priority priority;
}
