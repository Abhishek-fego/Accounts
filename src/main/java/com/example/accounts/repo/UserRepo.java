package com.example.accounts.repo;

import com.example.accounts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {

    @Query(value = "select * from users u where u.is_deleted = false", nativeQuery = true)
    public List<User> findAllActiveUsers();

    @Query(value = "select * from users u where u.username = ?1",nativeQuery = true)
    public User findUserByUserName(String username);
}
