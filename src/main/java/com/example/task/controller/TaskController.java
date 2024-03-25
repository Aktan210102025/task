package com.example.task.controller;

import com.example.task.dto.AnswerRequest;
import com.example.task.dto.TaskRequest;
import com.example.task.dto.TaskResponse;
import com.example.task.dto.UserResponse;
import com.example.task.entity.User;
import com.example.task.service.TaskService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/add")
    public void create(@RequestBody TaskRequest request){
        taskService.create(request);
    }
    @GetMapping("/all")
    public List<TaskResponse> all(){
        return taskService.findAll();
    }
    @GetMapping("/byId/{taskId}")
    public TaskResponse response(@PathVariable Long taskId){
        return taskService.findById(taskId);
    }

    @DeleteMapping("/delete/{taskId}")
    public void delete(@PathVariable Long taskId){
        taskService.delete(taskId);
    }

    @PostMapping("/answer")
    public Boolean answer(@RequestBody AnswerRequest request){
        return taskService.answer(request);
    }

}
