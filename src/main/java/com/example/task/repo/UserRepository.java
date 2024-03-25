package com.example.task.repo;

import com.example.task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndNicknameOrNicknameAndEmail(String s1, String s2, String s3, String a4);
    Optional<User> findByNickname(String nickname);
    void deleteByNickname(String nickname);
}
