package com.example.task.service.impl;

import com.example.task.config.EmailSenderService;
import com.example.task.config.JwtService;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TaskRepository taskRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService senderService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

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

    @Override
    public void register(String email, String password) {
        if (userRepository.findByEmail(email).isPresent())
            throw new BadRequestException("user with this email is already exist!");
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        String code = UUID.randomUUID().toString();
        user.setConfirmationCode(code);
        senderService.sendEmail(user.getEmail(), "Please, confirm ur email!", "http://localhost:8888/user/confirm?code=" + code);
        userRepository.save(user);
    }

    @Override
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new BadRequestException("user with this email is not exist!"));
        if (!user.getConfirmationCode().equals("confirmed"))
            throw new BadRequestException("confirm your email please!");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        }
        catch (Exception e){
            throw new BadRequestException("bad credentials");
        }
        return jwtService.generateToken(user);
    }

    @Override
    public String confirm(String code) {
        User user = userRepository.findByConfirmationCode(code).orElseThrow(() -> new BadRequestException("user with this code is not exist!"));
        user.setConfirmationCode("confirmed");
        userRepository.save(user);
        return "successfully confirmed!";
    }
}
