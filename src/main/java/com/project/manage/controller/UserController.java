package com.project.manage.controller;

import com.project.manage.Dto.UserResponseDTO;
import com.project.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getUserdetails(Authentication authentication){
        String currentName=authentication.getName();
        UserResponseDTO responseDTO= userService.getCurrentUser(currentName);
        return ResponseEntity.ok(responseDTO);
    }
}
