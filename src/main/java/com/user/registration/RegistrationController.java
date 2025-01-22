package com.user.registration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.registration.model.Role;
import com.user.registration.model.User;
import com.user.registration.service.RoleService;
import com.user.registration.service.UserService;

@RestController
public class RegistrationController {
	
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
		
	@PostMapping("/addUser")
	public User save(@RequestBody User user) {
		return userService.save(user);
	}
	
	@GetMapping("/showUsers")
	public List<User> findAll() {
		return userService.findAll();
	}
	
	@PostMapping("/addRole")
	public Role save(@RequestBody Role role) {
		return roleService.save(role);
	}
	
	@GetMapping("/showRoles")
	public List<Role> findAllRoles() {
		return roleService.findAll();
	}
	
	

}
