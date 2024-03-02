# Overview
implemented user service with spring boot 

# API
1. get all user :  
   endpoint: `/user/all`  
   method:`GET`  
   Response:Array of User  
2. add user:  
   endpoint: `/user`  
   method:`POST`  
   ```Signup Request DTO
   {
   "name":
   "email":
   "password":
   }
   ```
   response: void  
   This api response with void and reposnse status with OK(200)  
3. login:  
    endpoint: `/user/login`  
    method:`post`  
   ```Login Request DTO
   {
   "email":
   "password":
   }
   ```
   respone : JWT token  
4. Validate Token
   endpoint : '/user/validate'  
   send JWT token that received while login through header["Authentication"] params
    
