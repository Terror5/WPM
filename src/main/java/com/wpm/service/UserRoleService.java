package com.wpm.service;

import java.util.List;

import com.wpm.exception.UserRoleNotFound;
import com.wpm.model.UserRole;

public interface UserRoleService {
	
    public UserRole create(UserRole userRole);
    public UserRole delete(String id) throws UserRoleNotFound;
    public List<UserRole> findAll();
    public UserRole update(UserRole userRole) throws UserRoleNotFound;
    public UserRole findById(String id);

}