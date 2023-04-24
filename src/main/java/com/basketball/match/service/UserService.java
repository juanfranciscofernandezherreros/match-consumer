package com.basketball.match.service;

import com.basketball.match.entity.Fixtures;
import com.basketball.match.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void createUser(Fixtures user){
        userRepository.save(user);
    }

}
