package com.example.accounts.repo;

import com.example.accounts.model.Account;
import com.example.accounts.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction,Long> {

    @Query(value = "SELECT * from transaction t where t.user_id = ?1 and  t.account_id = ?2 and t.tenant_id=?3" , nativeQuery = true)
    public List<Transaction> getTransactionByUserIdAndAccount_id(long user_id,long account_id,String tenant_id);
}
