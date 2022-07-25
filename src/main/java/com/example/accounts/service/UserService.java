package com.example.accounts.service;

import com.example.accounts.dto.UserDto;
import com.example.accounts.model.*;
import com.example.accounts.repo.AccountRepo;
import com.example.accounts.repo.TransactionRepo;
import com.example.accounts.repo.UserRepo;
import com.example.accounts.tenant.TenantContext;
import com.example.accounts.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public String registerUser(UserDto userDto) {
        // User user = modelMapper.map(userDto, User.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //user.setPassword(encoder.encode(user.getPassword()));

//        User.builder()
//                .created_by(userDto.getUsername())
//                        .updated_by(userDto.getUsername())
//                                .created_at(new Date())
//                                        .updated_at(new Date())
//                                                .build();
//                user.setCreated_by(userDto.getUsername());
//        user.setUpdated_by(userDto.getUsername());
//        user.setCreated_at(new Date());
//        user.setUpdated_at(new Date());
//        user.set_deleted(false);
//        user.setTenant_id(TenantContext.getCurrentTenant());
        User user = User.builder()
                .created_by(userDto.getUsername())
                .username(userDto.getUsername())
                .password(encoder.encode(userDto.getPassword()))
                .updated_by(userDto.getUsername())
                .created_at(new Date())
                .updated_at(new Date())
                .tenant_id(TenantContext.getCurrentTenant())
                .build();

        userRepo.save(user);
        return "registered successfully!! ";

    }

//    public List<UserDto> getUser() {
//
//        List<User> users = userRepo.findAllActiveUsersByTenantId(TenantContext.getCurrentTenant());
//        return users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
//    }

    public PageResponse getPageUsers(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<User> users = userRepo.findAllActiveUsersByPage(TenantContext.getCurrentTenant(),pageable);
        List<User> userList = users.getContent();
        List<UserDto> content = userList.stream().map(user->modelMapper.map(user,UserDto.class)).collect(Collectors.toList());

        return PageResponse.builder()
                .content(content)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalElements(users.getTotalElements())
                .totalPages(users.getTotalPages())
                .last(users.isLast())
                .build();
    }

    public String updateUser(UserDto userDto, long id) throws Exception {
        User repoUser = userRepo.findById(id).orElseThrow(() -> new Exception("No user exists with the given id"));
        User user = modelMapper.map(userDto, User.class);
        repoUser.setUpdated_at(new Date());
        repoUser.setUsername(user.getUsername());
        repoUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepo.save(repoUser);
        return "updated successfully!!";

    }



    public String deleteUser(long id) throws Exception {
        User user = userRepo.findById(id).orElseThrow(() -> new Exception("No user with given Id"));
        user.set_deleted(true);
        userRepo.save(user);
        return "Deleted successfully!!";
    }

    public Entity getEntity(long id) {
        String tenant_id = TenantContext.getCurrentTenant();
        List<Account> accounts = accountRepo.getAccountsByUserIdAndTenantId(id, tenant_id);
        List<AccountResponse> responses = new ArrayList<>();
        List<Transaction> transactionList = new ArrayList<>();
        accounts.forEach(account -> transactionList.addAll(transactionRepo.getTransactionByUserIdAndAccount_id(id, account.getId(), tenant_id)));
        accounts.forEach(account -> {
            AccountResponse accountResponse = modelMapper.map(account, AccountResponse.class);
            transactionList.stream()
                    .filter(t -> t.account_id == account.getId())
                    .forEach(transaction -> {
                        if (transaction.getType().equals(CREDIT)) {
                            accountResponse.setCreditAmount(accountResponse.getCreditAmount().add(transaction.getAmount()));
                            accountResponse.setNumberOfCreditTransactions(accountResponse.getNumberOfCreditTransactions() + 1);
                        } else if (transaction.getType().equals(DEBIT)) {
                            accountResponse.setDebitAmount(accountResponse.getDebitAmount().add(transaction.getAmount()));
                            accountResponse.setNumberOfDebitTransactions(accountResponse.getNumberOfDebitTransactions() + 1);
                        }
                    });
            responses.add(accountResponse);
        });
        BigDecimal totalBalance = accounts.stream().map(b -> b.currentBalance).reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Entity(responses.size(), totalBalance, responses);
    }

    public String loginUser(AuthRequest authRequest) throws Exception {
        User user = userRepo.findUserByUserName(authRequest.getUsername());
        if (user == null) {
            throw new Exception("User not found");
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        return jwtUtil.generateToken(userDetails, TenantContext.getCurrentTenant());
    }
}
