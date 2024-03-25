package com.example.task.repo;

import com.example.task.dto.TaskResponse;
import com.example.task.entity.Task;
import com.example.task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    void deleteAllByAuthorNickname(String author);
}
