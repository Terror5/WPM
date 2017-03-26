package com.wpm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wpm.exception.RoleNotFound;
import com.wpm.model.UserProjectRole;
import com.wpm.model.UserProjectRolePK;
import com.wpm.repository.UserProjectRoleRepository;

@Service
public class UserProjectRoleServiceImp implements UserProjectRoleService {

	@Resource
	private UserProjectRoleRepository userProjectRoleRepository;
	
	@Transactional
	public UserProjectRole create(UserProjectRole userProjectRole) {
		UserProjectRole createdUserProjectRole = userProjectRole;
		return userProjectRoleRepository.save(createdUserProjectRole);
	}

	@Transactional(rollbackFor=RoleNotFound.class)
	public UserProjectRole delete(UserProjectRolePK id) {
		UserProjectRole deletedUserProjectRole = userProjectRoleRepository.findOne(id);		
		userProjectRoleRepository.delete(deletedUserProjectRole);
		return deletedUserProjectRole;
	}

	@Transactional
	public List<UserProjectRole> findAll() {
		return userProjectRoleRepository.findAll();
	}

	@Transactional(rollbackFor=RoleNotFound.class)
	public UserProjectRole update(UserProjectRole userProjectRole) {
		UserProjectRole updatedUserProjectRole = userProjectRoleRepository.findOne(userProjectRole.getId());
		
		//updatedRole.setCreateProject(Role.getCreateProject());
		//updatedRole.setModifyProject(Role.getModifyProject());
		//updatedRole.setDeleteProject(Role.getDeleteProject());
		//updatedRole.setSuper_(Role.getSuper_());
		return updatedUserProjectRole;
	}

	@Transactional
	public UserProjectRole findById(UserProjectRolePK id) {
		return userProjectRoleRepository.findOne(id);
	}

	@Transactional
	public List<UserProjectRole> createBatch(
			Iterable<UserProjectRole> userProjectRoleBatch) {
		return userProjectRoleRepository.save(userProjectRoleBatch);
	}

}
