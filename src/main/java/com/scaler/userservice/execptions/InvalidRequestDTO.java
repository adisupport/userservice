package com.scaler.userservice.execptions;


public class InvalidRequestDTO extends Exception{
    public InvalidRequestDTO(String message){
        super(message);
    }
    public InvalidRequestDTO(){
        super("Invalid Field Value");
    }
}
