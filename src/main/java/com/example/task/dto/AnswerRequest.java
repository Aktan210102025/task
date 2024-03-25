package com.example.task.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerRequest {
    private String nickname;
    private Long taskId;
    private String answer;
}
