package com.example.task.service;

import com.example.task.dto.RankingResponse;
import com.example.task.dto.UserRequest;
import com.example.task.dto.UserResponse;

import java.util.List;

public interface UserService {
    void createUser(UserRequest request);

    List<UserResponse> getAll();

    void delete(String nickname);

    List<RankingResponse> ranking(Boolean byPoint);
}
