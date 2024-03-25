package com.example.task.service;

import com.example.task.dto.AnswerRequest;
import com.example.task.dto.TaskRequest;
import com.example.task.dto.TaskResponse;

import java.util.List;

public interface TaskService {
    void create(TaskRequest request);

    List<TaskResponse> findAll();

    TaskResponse findById(Long taskId);

    void delete(Long taskId);

    Boolean answer(AnswerRequest request);
}
