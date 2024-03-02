package com.scaler.userservice.controllers;

import com.scaler.userservice.dtos.LoginRequestDTO;
import com.scaler.userservice.dtos.SignupRequestDTO;
import com.scaler.userservice.execptions.InvalidRequestDTO;
import com.scaler.userservice.execptions.NoUserFoundException;
import com.scaler.userservice.models.User;
import com.scaler.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;
    @Autowired
    UserController(@Qualifier("SelfUserService") UserService userService){
        this.userService = userService;
    }
    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO loginRequestDTO) throws NoUserFoundException, InvalidRequestDTO {
            return userService.login(loginRequestDTO.getEmail(),loginRequestDTO.getPassword());
    }
    @GetMapping("/all")
    List<User> allUser(){
        return userService.getAllUser();
    }

    @PostMapping
    ResponseEntity<User> signup(@RequestBody SignupRequestDTO signupRequestDTO) throws Exception{
        if(signupRequestDTO.notValidate()) throw new InvalidRequestDTO();
        User user = userService.addUser(signupRequestDTO.getName(),signupRequestDTO.getEmail(),signupRequestDTO.getPassword());
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("/validate")
    User validateToken(@RequestHeader("Authorization") String token){
        return userService.ValidateToken(token);
    }
}
