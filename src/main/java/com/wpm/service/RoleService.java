package com.wpm.service;

import java.util.List;

import com.wpm.exception.RoleNotFound;
import com.wpm.model.Role;

public interface RoleService {
	
    public Role create(Role role);
    public Role delete(String id) throws RoleNotFound;
    public List<Role> findAll();
    public Role update(Role role) throws RoleNotFound;
    public Role findById(String id);

}