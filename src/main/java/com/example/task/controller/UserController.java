package com.example.task.controller;

import com.example.task.dto.RankingResponse;
import com.example.task.dto.UserRequest;
import com.example.task.dto.UserResponse;
import com.example.task.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public void register(@RequestParam String email, @RequestParam String password){
        userService.register(email, password);
    }
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password){
        return userService.login(email, password);
    }
    @GetMapping("/confirm")
    public String confirm(@RequestParam String code){
        return userService.confirm(code);
    }

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

    @GetMapping("/ranking")
    public List<RankingResponse> ranking(@RequestParam Boolean byPoint){
        return userService.ranking(byPoint);
    }
}
