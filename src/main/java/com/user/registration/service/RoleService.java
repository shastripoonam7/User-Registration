package com.user.registration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.registration.model.Role;
import com.user.registration.repo.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepo;
	
	public Role save(Role role) {
		return roleRepo.save(role);
	}
	
	public List<Role> findAll(){
		return roleRepo.findAll();
	}
	
	public List<String> findAllByNames(){
		return this.findAll().stream().map(n->n.getName()).toList();
	}
}
