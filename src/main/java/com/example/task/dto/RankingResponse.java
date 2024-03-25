package com.example.task.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankingResponse {
    private String nickname;
    private Integer point;
    private Integer answeredTaskCount;
}
