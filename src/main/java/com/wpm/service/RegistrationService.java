package com.wpm.service;

import java.util.Collection;
import java.util.List;

import com.wpm.exception.UserNotFound;
import com.wpm.model.SqlUser;
import com.wpm.model.Team;



public interface RegistrationService {
	
	 public SqlUser create(SqlUser user);
     public SqlUser delete(String id) throws UserNotFound;
     public List<SqlUser> findAll();
     public SqlUser update(SqlUser user) throws UserNotFound;
     public SqlUser findById(String id);
     
     public List<SqlUser> findByFirstName(String firstName);
     public List<SqlUser> findByTeamIsNull();
     public List<SqlUser> findByIdUserIn(Collection<Integer> idUser);
     public List<SqlUser> findByTeam(Team team);

}
