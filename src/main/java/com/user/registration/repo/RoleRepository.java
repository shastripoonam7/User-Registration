package com.user.registration.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.registration.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
