package com.wpm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wpm.exception.WorkitemNotFound;
import com.wpm.model.Iteration;
import com.wpm.model.Project;
import com.wpm.model.Workitem;
import com.wpm.repository.WorkitemRepository;

@Service
public class WorkitemServiceImp implements WorkitemService {
	
	@Resource
	private WorkitemRepository workitemRepository;
	

	@Transactional
	public Workitem create(Workitem Workitem) {
		Workitem createdWorkitem = Workitem;
		//if(createdWorkitem.getIteration().getIdIteration() == -1)
		//{
		//	createdWorkitem.setIteration(null);
		//}
		
		/*if(createdWorkitem.getWorkitem().getIdWorkitem() == -1)      Optional
		{
			createdWorkitem.setIteration(null);
		} */
		
		
		return workitemRepository.save(createdWorkitem);
		
	}


	@Transactional(rollbackFor=WorkitemNotFound.class)
	public Workitem delete(int id) throws WorkitemNotFound {
		Workitem deletedWorkitem = workitemRepository.findOne(id);
		
		if(deletedWorkitem == null){
			throw new WorkitemNotFound();
		}
		
		workitemRepository.delete(deletedWorkitem);
		return deletedWorkitem;
	}


	@Transactional
	public List<Workitem> findAll() {
		return workitemRepository.findAll();
	}


	@Transactional(rollbackFor=WorkitemNotFound.class)
	public Workitem update(Workitem workitem) throws WorkitemNotFound {
		Workitem updatedWorkitem = workitemRepository.findOne(workitem.getIdWorkitem());
		
		if(updatedWorkitem == null){
			throw new WorkitemNotFound();
		}
		
		updatedWorkitem.setTitle(workitem.getTitle());
		updatedWorkitem.setDescription(workitem.getDescription());
		updatedWorkitem.setPriority(workitem.getPriority());
		updatedWorkitem.setSizeEstimate(workitem.getSizeEstimate());
		updatedWorkitem.setType(workitem.getType());
		updatedWorkitem.setProject(workitem.getProject());
		updatedWorkitem.setIteration(workitem.getIteration());

		return updatedWorkitem;
	}


	@Transactional
	public Workitem findById(int id) {
		return workitemRepository.findOne(id);
	}
	
	@Transactional
	public List<Workitem> findBytitel(String title){
		return workitemRepository.findByTitle(title);
	}


	@Transactional
	public List<Workitem> findByProjectAndIteration(Project project,
			Iteration iteration) {
		return workitemRepository.findByProjectAndIteration(project, iteration);
	}


	@Transactional
	public List<Workitem> findByProjectAndIterationAndFetchTasksEagerly(Project project, Iteration iteration, byte type) {
		return workitemRepository.findByProjectAndIterationAndFetchTasksEagerly(project, iteration, type);
	}

	@Transactional
	public List<Workitem> findAllAndFetchTasksEagerly() {
		return workitemRepository.findAllAndFetchTasksEagerly();
	}


	@Transactional
	public List<Workitem> findByProject(Project project) {
		return workitemRepository.findByProject(project);
	}


	@Transactional
	public List<Workitem> findByProjectAndTypeNot(Project project, byte type) {
		return workitemRepository.findByProjectAndTypeNot(project, type);
	}


	@Transactional
	public List<Workitem> findByProjectAndType(Project project, byte type) {
		return workitemRepository.findByProjectAndType(project, type);
	}


	@Transactional
	public List<Workitem> findAllAndFetchTasksEagerlyByProjectAndIteration(
			Project project, Iteration iteration) {
		return workitemRepository.findAllAndFetchTasksEagerlyByProjectAndIteration(project, iteration);
	}

	




}
