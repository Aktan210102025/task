package com.example.task.mapper;

import com.example.task.dto.TaskResponse;
import com.example.task.entity.Task;

import java.util.List;

public interface TaskMapper {
    List<TaskResponse> toResponses(List<Task> all);

    TaskResponse toResponse(Task task);
}
