package com.wpm.service;

import java.util.List;

import com.wpm.exception.ProjectNotFound;
import com.wpm.model.Project;
import com.wpm.model.Team;

public interface ProjectService {
        
        public Project create(Project project);
        public Project delete(int id) throws ProjectNotFound;
        public List<Project> findAll();
        public Project update(Project project) throws ProjectNotFound;
        public Project findById(int id);
        
        public List<Project> findBytitel(String titel);
    	public List<Project> findByTeamAndFetchRolesEagerly(Team team);

}
