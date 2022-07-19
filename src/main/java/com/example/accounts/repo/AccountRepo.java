package com.example.accounts.repo;

import com.example.accounts.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account,Long> {

    @Query(value = "SELECT * from account a where a.user_id = ?1 and a.tenant_id=?2" , nativeQuery = true)
    public List<Account> getAccountsByUserIdAndTenantId(long id,String tenant_id);
}
