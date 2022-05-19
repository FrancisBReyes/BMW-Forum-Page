package com.example.bmwreddit.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bmwreddit.dto.AuthenticationResponse;
import com.example.bmwreddit.dto.LoginRequest;
import com.example.bmwreddit.dto.RegisterRequest;
import com.example.bmwreddit.exception.SpringRedditException;
import com.example.bmwreddit.model.NotificationEmail;
import com.example.bmwreddit.model.User;
import com.example.bmwreddit.model.VerificationToken;
import com.example.bmwreddit.repository.UserRepository;
import com.example.bmwreddit.repository.VerificationTokenRepository;
import com.example.bmwreddit.security.JwtProvider;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import static com.example.bmwreddit.util.Constants.ACTIVATION_EMAIL;
import static java.time.Instant.now;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {
	
	//CONSTRUCTOR
	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
			VerificationTokenRepository verificationTokenRepository, MailContentBuilder mailContentBuilder,
			MailService mailService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.verificationTokenRepository = verificationTokenRepository;
		this.mailContentBuilder = mailContentBuilder;
		this.mailService = mailService;
	}
	
	//EMPTY CONSTRUCTOR 
	public AuthService() {
	}

	@Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private VerificationTokenRepository verificationTokenRepository;
    private MailContentBuilder mailContentBuilder;
    private MailService mailService; 
    private AuthenticationManager authenticationManager; 
    private JwtProvider jwtProvider; 
    

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        //CREATE USER OBJECT AND STORE IN DATABASE
    	User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);
        
        //ACTIVATING NEW ACCOUNT VIA EMAIL 
        String token = generateVerificationToken(user);
        
        String message = mailContentBuilder.build("Thank you for signing up to Spring Reddit, please click on the below url to activate your account : "
                + ACTIVATION_EMAIL + "/" + token);
        
        mailService.sendMail(new NotificationEmail("Please Activate your account", user.getEmail(), message));
    }

//	private String encodePassword(String password) {
//		return passwordEncoder.encode(password);
//	}
    
    //GENERATE A VERIFICATION TOKEN RIGHT AFTER WE SAVE THE USER TO THE DATABASE
    //AND SEND THAT TOKEN AS PART OF THE VERIFICATION EMAIL 
    private String generateVerificationToken(User user) {
        //UUID(UNIVERSALLY UNIQUE IDENTIFIER) --> GENERATE HEX DIGITS
    	String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(null, token, user, null);
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }
    
    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
    }
    
    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationTokenOptional = verificationTokenRepository.findByToken(token);
        verificationTokenOptional.orElseThrow(() -> new SpringRedditException("Invalid Token"));
        fetchUserAndEnable(verificationTokenOptional.get());
    }

    @Transactional
    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException("User Not Found with id - " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }
}
