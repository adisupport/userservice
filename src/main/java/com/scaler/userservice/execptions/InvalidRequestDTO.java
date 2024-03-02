package com.scaler.userservice.execptions;


public class InvalidRequestDTO extends Exception{
    public InvalidRequestDTO(){
        super("Invalid Field Value");
    }
}
