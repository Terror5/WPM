package com.wpm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wpm.exception.ProjectNotFound;
import com.wpm.model.Project;
import com.wpm.model.Team;
import com.wpm.repository.ProjectRepository;


@Service
public class ProjectServiceImp implements ProjectService {

	@Resource
	private ProjectRepository projectRepository;
	

	@Transactional
	public Project create(Project project) {
		Project createdproject = project;
		createdproject = projectRepository.save(createdproject);
		projectRepository.flush();
		return createdproject;
	}


	@Transactional(rollbackFor=ProjectNotFound.class)
	public Project delete(int id) throws ProjectNotFound {
		Project deletedProject = projectRepository.findOne(id);
		
		if(deletedProject == null){
			throw new ProjectNotFound();
		}
		
		projectRepository.delete(deletedProject);
		return deletedProject;
	}


	@Transactional
	public List<Project> findAll() {
		return projectRepository.findAll();
	}


	@Transactional(rollbackFor=ProjectNotFound.class)
	public Project update(Project project) throws ProjectNotFound {
		Project updatedProject = projectRepository.findOne(project.getIdProject());
		
		if(updatedProject == null){
			throw new ProjectNotFound();
		}
		
		updatedProject.setTitle(project.getTitle());
		updatedProject.setDescription(project.getDescription());
		updatedProject.setDateBegin(project.getDateBegin());
		updatedProject.setDateEnd(project.getDateEnd());
		updatedProject.setIterationCycle(project.getIterationCycle());
		return updatedProject;
	}


	@Transactional
	public Project findById(int id) {
		return projectRepository.findOne(id);
	}
	
	@Transactional
	public List<Project> findBytitel(String title){
		return projectRepository.findByTitle(title);
	}

	@Transactional
	public List<Project> findByTeamAndFetchRolesEagerly(Team team) {
		return projectRepository.findByTeamAndFetchRolesEagerly(team);
	}


	




}
