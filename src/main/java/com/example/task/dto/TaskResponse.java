package com.example.task.dto;

import com.example.task.entity.User;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TaskResponse {
    private Long id;
    private String name;
    private String description;
    private Integer point;
    private String answer;
    private Integer answeredUserCount;

    private String authorNickname;
}

