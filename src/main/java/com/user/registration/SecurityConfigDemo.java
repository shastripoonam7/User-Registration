package com.user.registration;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatchers;

import com.user.registration.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfigDemo  {
	
	@Autowired
	private UserService userService;
	
	static int count =0;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		System.out.println("Hi SecurityFilterChain:"+count++);
		http.
			authorizeHttpRequests(authorize->authorize.requestMatchers("/users").hasRole("ADMIN").
														requestMatchers("/home").authenticated().
														anyRequest().permitAll())
			 .formLogin(Customizer.withDefaults()) // Use Spring Security's default login page
	            .logout(logout -> logout
	                .logoutUrl("/logout") // Logout endpoint
	                .logoutSuccessUrl("/") // Redirect to / after logout
	                .permitAll() // Allow logout access
	            );
		return http.build();
	}
	
	
	

}
