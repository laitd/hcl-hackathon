package com.hcl.hackathon.demo.service;

import com.hcl.hackathon.demo.configuration.kafka.KafkaProducer;
import com.hcl.hackathon.demo.constants.StatusCode;
import com.hcl.hackathon.demo.domain.user.GetUsersResponse;
import com.hcl.hackathon.demo.domain.user.UserCreateRequest;
import com.hcl.hackathon.demo.domain.user.UserCreateResponse;
import com.hcl.hackathon.demo.entity.User;
import com.hcl.hackathon.demo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    KafkaProducer kafkaProducer;

    public UserCreateResponse createUser(UserCreateRequest request) {

        //send kafka
        kafkaProducer.sendMessage(request.getFirstName());

        if (request.getFirstName() == null)
            throw new RuntimeException("error");
        User user = new User();
        BeanUtils.copyProperties(request, user);
        userRepository.save(user);
        return UserCreateResponse.of(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getDesc(), null);
    }

    public GetUsersResponse getUsers() {
        final List<User> all = userRepository.findAll();
        return GetUsersResponse.of(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getDesc(), all);
    }
}
