package com.wpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

public interface RegistrationRepository extends JpaRepository<User, Integer>{
	
	
	

}
