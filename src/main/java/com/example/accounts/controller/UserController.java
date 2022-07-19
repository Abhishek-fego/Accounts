package com.example.accounts.controller;


import com.example.accounts.dto.UserDto;
import com.example.accounts.model.*;
import com.example.accounts.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDto userDto){

        return userService.registerUser(userDto);
    }

    @GetMapping("/user")
    public PageResponse getUsers(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        return userService.getPageUsers(pageNo,pageSize,sortBy,sortDir);
    }

    @PutMapping("/update/{id}")
    public String updateUser(@RequestBody UserDto userDto,@PathVariable long id ) throws Exception {
        return userService.updateUser(userDto,id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id) throws Exception {
        return userService.deleteUser(id);
    }

    @PostMapping("/login")
    public AuthResponse loginUser(@RequestBody AuthRequest authRequest) throws Exception {
        return new AuthResponse(userService.loginUser(authRequest));
    }

    @GetMapping("/entity/{id}")
    public EntityResponse getResponse(@PathVariable long id){
        return new EntityResponse(userService.getEntity(id));
    }
}
