package com.example.task.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Integer point;
    private String answer;
    @ManyToMany
    private List<User> answeredUsers;

    @ManyToOne
    private User author;
}
