package com.scaler.userservice.services;

import com.scaler.userservice.execptions.InvalidRequestDTO;
import com.scaler.userservice.execptions.NoUserFoundException;
import com.scaler.userservice.models.User;

import java.util.List;

public interface UserService {
    User addUser(String name,String email,String password) throws InvalidRequestDTO;
    List<User> getAllUser();
    String login(String email,String password) throws NoUserFoundException,InvalidRequestDTO;
//    void deleteUser(String token) throws RuntimeException;

    User ValidateToken(String token);
}
