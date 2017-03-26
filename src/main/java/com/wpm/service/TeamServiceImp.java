package com.wpm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wpm.exception.TeamNotFound;
import com.wpm.model.Team;
import com.wpm.repository.TeamRepository;


@Service
public class TeamServiceImp implements TeamService{

	@Resource
	private TeamRepository teamRepository;
	
	@Transactional
	public Team create(Team team) {
		Team createdTeam = team;
		return teamRepository.save(createdTeam);
	}

	@Transactional(rollbackFor=TeamNotFound.class)
	public Team delete(int id) throws TeamNotFound {
		Team deletedTeam = teamRepository.findOne(id);
		
		if(deletedTeam == null){
			throw new TeamNotFound();
		}
		
		teamRepository.delete(deletedTeam);
		return deletedTeam;
	}

	@Transactional
	public List<Team> findAll() {
		return teamRepository.findAll();
	}

	@Transactional(rollbackFor=TeamNotFound.class)
	public Team update(Team team) throws TeamNotFound {
		Team updatedTeam = teamRepository.findOne(team.getIdTeam());
		
		if(updatedTeam == null){
			throw new TeamNotFound();
		}
		
		updatedTeam.setTitle(team.getTitle());
		updatedTeam.setDescription(team.getDescription());
		return updatedTeam;
	}

	@Transactional
	public Team findById(int id) {
		return teamRepository.findOne(id);
	}


}
