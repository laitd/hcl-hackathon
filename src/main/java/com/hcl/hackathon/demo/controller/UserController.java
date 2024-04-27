package com.hcl.hackathon.demo.controller;

import com.hcl.hackathon.demo.domain.user.GetUsersResponse;
import com.hcl.hackathon.demo.domain.user.UserCreateRequest;
import com.hcl.hackathon.demo.domain.user.UserCreateResponse;
import com.hcl.hackathon.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserCreateResponse> create(@RequestBody UserCreateRequest userCreateRequest){
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(userCreateRequest));
    }

    @GetMapping
    public ResponseEntity<GetUsersResponse> create(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }
}
