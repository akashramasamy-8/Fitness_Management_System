package com.project.manage.service;

import com.project.manage.exception.EmailAlreadyExistsException;
import com.project.manage.exception.UserNameAlreadyExistsException;
import com.project.manage.model.User;
import com.project.manage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailSenderService emailSenderService;

    public void registerUser(User user) {
        if(userRepository.existsByName(user.getName())){
            throw new UserNameAlreadyExistsException("Usernmae already taken");
        }
        if(userRepository.existsByEmail(user.getEmail())){
            throw new EmailAlreadyExistsException("Email already in use");
        }
        if(user.getPassword().length()<6){
            throw new IllegalArgumentException("Password must be atleast 6 characters long");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        String subject = "Registration Successful";
        String body = "Dear " + user.getName() + ",\n\n" +
                "You have successfully registered as a " + user.getRole().name().toLowerCase() + " on our Fitness Platform.\n\n" +
                "Welcome aboard!\nFitness Team";

        emailSenderService.sendRegistrationEmail(user.getEmail(),subject,body);
    }


}
