package com.wpm.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wpm.model.SqlUser;
import com.wpm.model.Team;

public interface UserRepository extends JpaRepository<SqlUser, String> {
		
	public List<SqlUser> findByFirstName(String firstName);
	public List<SqlUser> findByTeamIsNull(); 
	public List<SqlUser> findByIdUserIn(Collection<Integer> idUser);
	public SqlUser findByeMail(String eMail);
	public List<SqlUser> findByTeam(Team team);
	
	@Query("SELECT u FROM SqlUser u LEFT JOIN FETCH u.roles WHERE u.idUser = :idUser")
	public SqlUser findByIdAndFetchRolesEagerly(@Param("idUser") String idUser);
	
}