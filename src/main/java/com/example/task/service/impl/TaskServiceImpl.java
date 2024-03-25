package com.example.task.service.impl;

import com.example.task.dto.TaskRequest;
import com.example.task.dto.TaskResponse;
import com.example.task.entity.Task;
import com.example.task.entity.User;
import com.example.task.exception.BadRequestException;
import com.example.task.mapper.TaskMapper;
import com.example.task.repo.TaskRepository;
import com.example.task.repo.UserRepository;
import com.example.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;
    @Override
    public void create(TaskRequest request) {
        Optional<User> user = userRepository.findByNickname(request.getAuthorNickname());
        if (user.isEmpty())
            throw new BadRequestException("user with this nickname is not found!");

        Task task = new Task();
        task.setAnswer(request.getAnswer());
        task.setName(request.getName());
        task.setAuthor(user.get());
        task.setDescription(request.getDescription());
        task.setPoint(request.getPoint());
        taskRepository.save(task);

    }

    @Override
    public List<TaskResponse> findAll() {
        return taskMapper.toResponses(taskRepository.findAll());
    }

    @Override
    public TaskResponse findById(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty())
            throw new BadRequestException("task is not found!");
        return taskMapper.toResponse(task.get());
    }

    @Override
    public void delete(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
