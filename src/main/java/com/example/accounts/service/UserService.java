package com.example.accounts.service;

import com.example.accounts.dto.UserDto;
import com.example.accounts.model.*;
import com.example.accounts.repo.AccountRepo;
import com.example.accounts.repo.TransactionRepo;
import com.example.accounts.repo.UserRepo;
import com.example.accounts.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;

    private final AccountRepo accountRepo;

    private final TransactionRepo transactionRepo;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final MyUserDetailsService userDetailsService;


    ModelMapper modelMapper = new ModelMapper();

    public UserService(UserRepo userRepo, AccountRepo accountRepo, TransactionRepo transactionRepo, AuthenticationManager authenticationManager, JwtUtil jwtUtil, MyUserDetailsService userDetailsService) {
        this.userRepo = userRepo;
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    public final String CREDIT = "CREDIT";
    public final String DEBIT = "DEBIT";

    public String registerUser(UserDto userDto){
        User user = modelMapper.map(userDto,User.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setCreated_by(userDto.getUsername());
        user.setUpdated_by(userDto.getUsername());
        user.setCreated_at(new Date());
        user.setUpdated_at(new Date());
        user.setIs_deleted(false);
        userRepo.save(user);
        return "registered successfully!! ";

    }

    public List<UserDto> getUser(){
        List<User> users = userRepo.findAllActiveUsers();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user:users){
            userDtos.add(modelMapper.map(user,UserDto.class));
        }
        return userDtos;
    }

    public String updateUser(UserDto userDto,long id) {
        if (userRepo.findById(id).isPresent()){
            User user = modelMapper.map(userDto,User.class);
            user.setUpdated_at(new Date());
            userRepo.save(user);
            return "updated successfully!!";
        }
        else{
            return "No user exists with the given id";
        }

    }

    public String deleteUser(long id) throws Exception {
        User user = userRepo.findById(id).orElseThrow(()->new Exception("No user with given Id"));
        user.setIs_deleted(true);
        userRepo.save(user);
        return "Deleted successfully!!";
    }

    public Entity getEntity(long id){

        List<Account> accounts = accountRepo.getAccountsByUserId(id);
        List<List<Transaction>> transactions = new ArrayList<>();
        for(Account account: accounts){
            List<Transaction> transactions1 = transactionRepo.getTransactionByUserIdAndAccount_id(id,account.getId());
            transactions.add(transactions1);
        }
        List<AccountResponse> responses = new ArrayList<>();
        for (int i=0;i<accounts.size();i++){
            AccountResponse accountResponse = modelMapper.map(accounts.get(i),AccountResponse.class);
            BigDecimal debit = new BigDecimal(0);
            BigDecimal credit = new BigDecimal(0);
            long debitTransactions = 0;
            long creditTransactions = 0;
            for(Transaction transaction : transactions.get(i)){
                if (transaction.getType().equals(CREDIT)){
                    credit = credit.add(transaction.getAmount());
                    creditTransactions++;
                } else if (transaction.getType().equals(DEBIT)) {
                    debit = debit.add(transaction.getAmount());
                    debitTransactions++;
                }
            }
            accountResponse.setNumberOfCreditTransactions(creditTransactions);
            accountResponse.setNumberOfDebitTransactions(debitTransactions);
            accountResponse.setDebitAmount(debit);
            accountResponse.setCreditAmount(credit);
            responses.add(accountResponse);
        }
        BigDecimal totalBalance = new BigDecimal(0);
        for (Account account : accounts){
            totalBalance = totalBalance.add(account.getCurrentBalance());
        }
        return new Entity(responses.size(),totalBalance,responses);
    }

    public String loginUser(AuthRequest authRequest) throws Exception {
        User user = userRepo.findUserByUserName(authRequest.getUsername());
        if (user == null){
            throw new Exception("User not found");
        }
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));

        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        return jwtUtil.generateToken(userDetails);
    }
}
