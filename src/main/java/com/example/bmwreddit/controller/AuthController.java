package com.example.bmwreddit.controller;

/*
 So we have created a RestController and inside this controller, we first created a method that will be invoked whenever a POST request is made to register the user’s in our application.

The API call should contain the request body which is of type RegisterRequest.
Through this class we are transferring the user details like username, password and email as part of the RequestBody.
We call this kind of classes as a DTO (Data Transfer Object)
 */

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bmwreddit.dto.AuthenticationResponse;
import com.example.bmwreddit.dto.LoginRequest;
import com.example.bmwreddit.dto.RegisterRequest;
import com.example.bmwreddit.service.AuthService;

import lombok.AllArgsConstructor;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

	@Autowired
    private AuthService authService;

	// endpooint for signup
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Succesful", OK);
    }
    
    // endpoint for login
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
    	return authService.login(loginRequest); 
    }
    
    // endpoint for verify account (Spring Security) 
    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successully", OK);
    }
}
