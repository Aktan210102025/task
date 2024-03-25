package com.example.task.controller;

import com.example.task.dto.UserRequest;
import com.example.task.dto.UserResponse;
import com.example.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public void createUser(@RequestBody UserRequest request){
        userService.createUser(request);
    }
    @GetMapping("/all")
    public List<UserResponse> all(){
        return userService.getAll();
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam String nickname){
        userService.delete(nickname);
    }
}
