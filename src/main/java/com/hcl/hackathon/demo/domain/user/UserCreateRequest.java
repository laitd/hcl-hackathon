package com.hcl.hackathon.demo.domain.user;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class UserCreateRequest {
    private String firstName;
    private String lastName;

}
