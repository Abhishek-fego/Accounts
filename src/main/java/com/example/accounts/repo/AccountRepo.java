package com.example.accounts.repo;

import com.example.accounts.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account,Long> {

    @Query(value = "SELECT * from account a where a.user_id = ?1" , nativeQuery = true)
    public List<Account> getAccountsByUserId(long id);
}
