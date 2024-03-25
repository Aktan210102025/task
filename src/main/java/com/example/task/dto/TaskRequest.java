package com.example.task.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TaskRequest {
    private String authorNickname;
    private String name;
    private String description;
    private Integer point;
    private String answer;

}
