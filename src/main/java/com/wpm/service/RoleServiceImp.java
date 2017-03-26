package com.wpm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wpm.exception.RoleNotFound;
import com.wpm.model.Role;
import com.wpm.repository.RoleRepository;


@Service
public class RoleServiceImp implements RoleService{

	@Resource
	private RoleRepository RoleRepository;
	
	@Transactional
	public Role create(Role Role) {
		Role createdRole = Role;
		return RoleRepository.save(createdRole);
	}

	@Transactional(rollbackFor=RoleNotFound.class)
	public Role delete(String id) throws RoleNotFound {
		Role deletedRole = RoleRepository.findOne(id);
		
		if(deletedRole == null){
			throw new RoleNotFound();
		}
		
		RoleRepository.delete(deletedRole);
		return deletedRole;
	}

	@Transactional
	public List<Role> findAll() {
		return RoleRepository.findAll();
	}

	@Transactional(rollbackFor=RoleNotFound.class)
	public Role update(Role Role) throws RoleNotFound {
		Role updatedRole = RoleRepository.findOne(Role.getIdRole());
		
		if(updatedRole == null){
			throw new RoleNotFound();
		}
				
		updatedRole.setTitle(Role.getTitle());
		//updatedRole.setCreateProject(Role.getCreateProject());
		//updatedRole.setModifyProject(Role.getModifyProject());
		//updatedRole.setDeleteProject(Role.getDeleteProject());
		//updatedRole.setSuper_(Role.getSuper_());
		return updatedRole;
	}

	@Transactional
	public Role findById(String id) {
		return RoleRepository.findOne(id);
	}

}
