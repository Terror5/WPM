package com.wpm.service;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wpm.exception.UserNotFound;
import com.wpm.model.SqlUser;
import com.wpm.model.Team;
import com.wpm.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Resource
	private UserRepository userRepository;
	

	@Transactional
	public SqlUser create(SqlUser user) {
		SqlUser createdUser = user;
		if(createdUser.getTeam() != null && createdUser.getTeam().getIdTeam() == -1){
			createdUser.setTeam(null);
		}
		return userRepository.save(createdUser);
	}


	@Transactional(rollbackFor=UserNotFound.class)
	public SqlUser delete(String id) throws UserNotFound {
		SqlUser deletedUser = userRepository.findOne(id);
		
		if(deletedUser == null){
			throw new UserNotFound();
		}
		
		userRepository.delete(deletedUser);
		return deletedUser;
	}


	@Transactional
	public List<SqlUser> findAll() {
		return userRepository.findAll();
	}


	@Transactional(rollbackFor=UserNotFound.class)
	public SqlUser update(SqlUser user) throws UserNotFound {
		SqlUser updatedUser = userRepository.findOne(user.getIdUser());
		
		if(updatedUser == null){
			throw new UserNotFound();
		}
		
		updatedUser.setFirstName(user.getFirstName());
		updatedUser.setLastName(user.getLastName());
		updatedUser.seteMail(user.geteMail());
		updatedUser.setActive(user.getActive());
		updatedUser.setPwHash(user.getPwHash());
		if(user.getTeam() != null){
			if(user.getTeam().getIdTeam() == -1){
				updatedUser.setTeam(null);
			} 
			else {
				updatedUser.setTeam(user.getTeam());
			}
		}
		
		if(user.getProject() != null){
			if(user.getProject().getIdProject() == -1){
				updatedUser.setProject(null);
			} 
			else {
				updatedUser.setProject(user.getProject());
			}
		}
		return updatedUser;
	}


	@Transactional
	public SqlUser findById(String id) {
		return userRepository.findOne(id);
	}
	
	@Transactional
	public List<SqlUser> findByFirstName(String firstName){
		return userRepository.findByFirstName(firstName);
	}


	@Transactional
	public List<SqlUser> findByTeamIsNull() {
		return userRepository.findByTeamIsNull();
	}
	
	@Transactional
	public List<SqlUser> findByIdUserIn(Collection<Integer> idUser){
		return userRepository.findByIdUserIn(idUser);
	}


	@Transactional
	public List<SqlUser> findByTeam(Team team) {
		return userRepository.findByTeam(team);
	}


	@Transactional
	public SqlUser findByIdAndFetchRolesEagerly(String idUser) {
		return userRepository.findByIdAndFetchRolesEagerly(idUser);
	}


	



}
