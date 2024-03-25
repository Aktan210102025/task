package com.example.task.mapper.impl;

import com.example.task.dto.UserResponse;
import com.example.task.entity.User;
import com.example.task.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public List<UserResponse> toResponses(List<User> all) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user: all)
            userResponses.add(toResponse(user));
        return userResponses;
    }

    private UserResponse toResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setPoint(user.getPoints());
        userResponse.setEmail(user.getEmail());
        userResponse.setNickname(user.getNickname());
        userResponse.setAnsweredTaskCount(user.getAnsweredTasks()!=null? user.getAnsweredTasks().size(): 0);
        return userResponse;
    }
}
