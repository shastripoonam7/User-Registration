package com.user.registration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigDemo  {
	
	
	
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
	
	@Bean
	public AuthenticationManager authenticationManager(BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService) {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(bCryptPasswordEncoder);
		provider.setUserDetailsService(userDetailsService);
		return new ProviderManager(provider);
		
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
