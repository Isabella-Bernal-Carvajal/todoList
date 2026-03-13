package com.example.todolist.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todolist.domain.Priority;
import com.example.todolist.domain.Status;
import com.example.todolist.dto.TaskRequestDTO;
import com.example.todolist.dto.TaskResponseDTO;
import com.example.todolist.dto.TaskUpdateDTO;
import com.example.todolist.entity.Task;
import com.example.todolist.mapper.TaskMapper;
import com.example.todolist.repository.TaskRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional

public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    @Transactional
    public TaskResponseDTO createTask(TaskRequestDTO requestDTO){
        
        Task task = new Task();
        
        task.setTitle(requestDTO.getTitle());
        task.setDescription(requestDTO.getDescription());

        if (requestDTO.getStatus() != null) {
            task.setStatus(requestDTO.getStatus());
        }else{
            task.setStatus(Status.TODO);
        }

        if (requestDTO.getPriority() != null) {
            task.setPriority(requestDTO.getPriority());
        }else{
            task.setPriority(Priority.UNDEFINED);
        }

        Task savedTask = taskRepository.save(task);

        return taskMapper.mapToResponse(savedTask);
    }


    @Transactional
    public List<TaskResponseDTO> taskList(){
        List<Task> tasks = taskRepository.findByDeletedAtIsNull();
        List<TaskResponseDTO> response = new ArrayList<>();

        for(Task task : tasks){
            response.add(taskMapper.mapToResponse(task));
        }
        return response;
    }


    public TaskResponseDTO searchById(Long id){
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task no found"));

        return taskMapper.mapToResponse(task);
    }


    @Transactional
    public TaskResponseDTO updateTask(Long id, TaskUpdateDTO dto){
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Task no found"));

        if (dto.getTitle() != null) {
            task.setTitle(dto.getTitle());
        }

        if (dto.getDescription() != null) {
            task.setDescription(dto.getDescription());
        }

        if (dto.getPriority() != null) {
            task.setPriority(dto.getPriority());
        }

        if (dto.getStatus() != null) {
            task.setStatus(dto.getStatus());
        }

        taskRepository.save(task);
        return taskMapper.mapToResponse(task);
    }


    public void deleteTask(Long id){
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Task alredy found"));

        task.setDeletedAt(LocalDateTime.now());
        taskRepository.save(task);
    }
}
