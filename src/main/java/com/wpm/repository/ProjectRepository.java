package com.wpm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wpm.model.Project;
import com.wpm.model.Team;


public interface ProjectRepository extends JpaRepository<Project,Integer>{
	
	public List<Project> findByTitle(String title);
	
	@Query("SELECT DISTINCT p FROM Project p LEFT JOIN FETCH p.userProjectRoles u WHERE p.team = :team")
	public List<Project> findByTeamAndFetchRolesEagerly(@Param("team") Team team);
}
