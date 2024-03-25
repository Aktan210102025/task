package com.example.task.service.impl;

import com.example.task.dto.RankingResponse;
import com.example.task.dto.UserRequest;
import com.example.task.dto.UserResponse;
import com.example.task.entity.User;
import com.example.task.exception.BadRequestException;
import com.example.task.mapper.UserMapper;
import com.example.task.repo.TaskRepository;
import com.example.task.repo.UserRepository;
import com.example.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TaskRepository taskRepository;
    @Override
    public void createUser(UserRequest request) {
        if (userRepository.findByEmailAndNicknameOrNicknameAndEmail(
                request.getEmail(), request.getNickname(), request.getNickname(), request.getEmail()).isPresent()
        ){
            throw new BadRequestException("this email/nickname is already exist!");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setNickname(request.getNickname());
        user.setPoints(0);
        userRepository.save(user);

    }

    @Override
    public List<UserResponse> getAll() {
        return userMapper.toResponses(userRepository.findAll());
    }

    @Override
    public void delete(String nickname) {
        taskRepository.deleteAllByAuthorNickname(nickname);
        userRepository.deleteByNickname(nickname);
    }

    @Override
    public List<RankingResponse> ranking(Boolean byPoint) {
        return userMapper.rankingResponses(byPoint? userRepository.findAllByOrderByPointsDesc(): userRepository.findAllUsersWithAnsweredTasksCountSorted());
    }
}
