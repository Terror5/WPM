package com.wpm.service;

import java.util.List;

import com.wpm.exception.TeamNotFound;
import com.wpm.model.Team;

public interface TeamService {
	
    public Team create(Team team);
    public Team delete(int id) throws TeamNotFound;
    public List<Team> findAll();
    public Team update(Team team) throws TeamNotFound;
    public Team findById(int id);
    

}
