package com.wpm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wpm.exception.UserRoleNotFound;
import com.wpm.model.UserRole;
import com.wpm.repository.UserRoleRepository;


@Service
public class UserRoleServiceImp implements UserRoleService{

	@Resource
	private UserRoleRepository userRoleRepository;
	
	@Transactional
	public UserRole create(UserRole userRole) {
		UserRole createdUserRole = userRole;
		return userRoleRepository.save(createdUserRole);
	}

	@Transactional(rollbackFor=UserRoleNotFound.class)
	public UserRole delete(String id) throws UserRoleNotFound {
		UserRole deletedUserRole = userRoleRepository.findOne(id);
		
		if(deletedUserRole == null){
			throw new UserRoleNotFound();
		}
		
		userRoleRepository.delete(deletedUserRole);
		return deletedUserRole;
	}

	@Transactional
	public List<UserRole> findAll() {
		return userRoleRepository.findAll();
	}

	@Transactional(rollbackFor=UserRoleNotFound.class)
	public UserRole update(UserRole userRole) throws UserRoleNotFound {
		/*UserRole updatedUserRole = userRoleRepository.findOne(userRole.getIdRole());
		
		if(updatedUserRole == null){
			throw new UserRoleNotFound();
		}
				
		return updatedUserRole; */
		return userRole;
	}

	@Transactional
	public UserRole findById(String id) {
		return userRoleRepository.findOne(id);
	}

}
