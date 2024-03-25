package com.example.task.mapper;

import com.example.task.dto.RankingResponse;
import com.example.task.dto.UserResponse;
import com.example.task.entity.User;

import java.util.List;

public interface UserMapper {
    List<UserResponse> toResponses(List<User> all);

    List<RankingResponse> rankingResponses(List<User> users);
}
