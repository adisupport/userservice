package com.scaler.userservice.execptions;

public class NoUserFoundException extends Exception{
    public NoUserFoundException(String email,String password){
        super("No User Found With Email: `"+email+"` and Password : `"+password+"`");
    }
}
