package com.example.bmwreddit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * CLASS HOLDS THE COMPLETE SECURITY CONFIGURATION OF OUR APPLICATION
 */

//THIS IS THE MAIN ANNOTATION WHICH ENABLES THE WEB SECURITY MODULE IN OUR PROJECT
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	//WEBSECURITYCONFIGURERADAPTER --> PROVIDES US THE DEFAULT SECURITY CONFIGURATIONS
	//WHICH WE CAN OVERRIDE IN OUR SECURITYCONFIG AND CUSTOMIZE THEM

	//UserDetailsService --> fetches the user information from our MySQL Database
	private UserDetailsService userDetailsService; 
	
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	//CONFIGURATIONS --> WE HAVE THE CONFIGURE METHOD WHICH WE HAVE OVERRIDED FROM THE BASE CLASS WHICH TAKES HTTPSECURITY AS AN ARGUMENT
	//WE ARE CONFIGURING SPRING TO ALLOW ALL THE REQUESTS WHICH MATCH THE ENDPOINT "/API/AUTH/***, AS THESE ENDPOINTS ARE USED FOR AUTHENTICATION
	//AND REGISTRATION WE DONT EXPECT THE USER TO BE AUHENTICATED AT THAT POINT OF TIME
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
    
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    	authenticationManagerBuilder.userDetailsService(userDetailsService)
    		.passwordEncoder(passwordEncoder());
    }

    //PASSWORDENCODER --> NOW BEFORE STORING THE USER IN THE DATABASE, WE IDEALLY WANT TO ENCODE THE PASSWORDS.
    //ONE OF THE BEST HASHING ALGORITHMS FOR PASSWORDS IS THE BCYRPT ALGORITHM
    //WE ARE USING THE BCRYPTPASSWORDENCODER CLASS PROVIDED BY SPRING SECURITY
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
