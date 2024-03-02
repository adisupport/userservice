package com.scaler.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    String email;
    String password;
    public Boolean validate(){
        return !email.isEmpty() && !password.isEmpty();
    }
}
