package com.user.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.user.registration.model.User;
import com.user.registration.service.RoleService;
import com.user.registration.service.UserService;

@Controller
public class ViewBasedRegistrationController {
	
	@Autowired
	UserService userService;
	
	@Autowired 
	RoleService roleService;

	@GetMapping("/")
	public String viewHomePage(Model model) {
        return "addUser";
    }
	
	@GetMapping("/home")
	public String addUserPage(Model model) {
        return "home";
    }
	
	@GetMapping("/registerationPage")
	public String registrationPage(Model model) {
		model.addAttribute("roles",roleService.findAll());
		return "register";
	}
	
	@PostMapping("/registerUser")
	public String registerUser(@ModelAttribute("user") User user,Model model) {
		System.out.println("Trying to save");
		userService.save(user);
		String message ="User Record saved successfully!";
		model.addAttribute("message",message);
		return "register";
		
		
	}
	
	@GetMapping("/users")
	public String registeredUsers(Model model) {
		 model.addAttribute("users",userService.findAll());
		return "registeredUsers";
	}
}
