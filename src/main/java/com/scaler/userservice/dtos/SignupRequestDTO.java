package com.scaler.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDTO {
    String name;
    String email;
    String password;
    public Boolean notValidate(){
        return password.isEmpty() || name.isEmpty() || email.isEmpty();
    }
}
