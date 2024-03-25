package com.example.task.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String email;
    private String nickname;
    private Integer point;
    private Integer answeredTaskCount;

}
