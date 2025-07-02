package com.project.manage.service;

import com.project.manage.Dto.UserResponseDTO;
import com.project.manage.model.Role;
import com.project.manage.model.User;
import com.project.manage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO getCurrentUser(String username){
        Optional<User> user=userRepository.findByName(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("No user found");
        }
        User user1=user.get();
        String name=user1.getName();
        String email=user1.getEmail();
        Role role=user1.getRole();
        UserResponseDTO userResponseDTO=new UserResponseDTO(name,email,role);
        return userResponseDTO;
    }
}
