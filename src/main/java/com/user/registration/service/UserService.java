package com.user.registration.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.user.registration.model.Role;
import com.user.registration.model.User;
import com.user.registration.repo.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepo;
	
	public User save(User user) {
		return userRepo.save(user);
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.findByEmail(username);
		List<String> roleDescription = user.getRoles().stream().map(role->role.getName()).collect(Collectors.toList());
		System.out.println("RoleDescription:"+roleDescription);
		System.out.println("I am in loadUserByUsername"+user.getRoles());
		if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    "{noop}"+user.getPassword(),
                    mapRolesToAuthorities(roleDescription));
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
	}
	
	private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <String> roles) {
        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
        return mapRoles;
    }
	
}
