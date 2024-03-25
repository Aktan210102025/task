package com.example.task.mapper.impl;

import com.example.task.dto.TaskResponse;
import com.example.task.entity.Task;
import com.example.task.mapper.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public List<TaskResponse> toResponses(List<Task> all) {
        List<TaskResponse> taskResponses = new ArrayList<>();
        for (Task task: all)
            taskResponses.add(toResponse(task));
        return taskResponses;
    }

    @Override
    public TaskResponse toResponse(Task task) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setName(task.getName());
        taskResponse.setAnswer(task.getAnswer());
        taskResponse.setPoint(task.getPoint());
        taskResponse.setDescription(task.getDescription());
        taskResponse.setAnsweredUserCount(task.getAnsweredUsers()!=null? task.getAnsweredUsers().size(): 0);
        taskResponse.setAuthorNickname(task.getAuthor().getNickname());
        return taskResponse;
    }
}
