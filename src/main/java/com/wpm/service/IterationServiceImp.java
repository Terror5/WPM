package com.wpm.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wpm.exception.IterationNotFound;
import com.wpm.model.Iteration;
import com.wpm.model.Project;
import com.wpm.repository.IterationRepository;


@Service
public class IterationServiceImp implements IterationService {

	@Resource
	private IterationRepository IterationRepository;
	

	@Transactional
	public Iteration create(Iteration iteration) {
		Iteration createdIteration = iteration;
		return IterationRepository.save(createdIteration);
	}


	@Transactional(rollbackFor=IterationNotFound.class)
	public Iteration delete(int id) throws IterationNotFound {
		Iteration deletedIteration = IterationRepository.findOne(id);
		
		if(deletedIteration == null){
			throw new IterationNotFound();
		}
		
		IterationRepository.delete(deletedIteration);
		return deletedIteration;
	}


	@Transactional
	public List<Iteration> findAll() {
		return IterationRepository.findAll();
	}


	@Transactional(rollbackFor=IterationNotFound.class)
	public Iteration update(Iteration iteration) throws IterationNotFound {
		Iteration updatedIteration = IterationRepository.findOne(iteration.getIdIteration());
		
		if(updatedIteration == null){
			throw new IterationNotFound();
		}
		
		updatedIteration.setEvaluation(iteration.getEvaluation());
		updatedIteration.setStartIteration(iteration.getStartIteration());
		updatedIteration.setEndIteration(iteration.getEndIteration());
		updatedIteration.setIdIteration(iteration.getIdIteration());
		return updatedIteration;
	}

	
	@Transactional
	public Iteration findById(int id) {
		return IterationRepository.findOne(id);
	}


	@Transactional
	public List<Iteration> findBytitel(String titel) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Iteration findCurrentIteration(Project project, Date date) {
		return IterationRepository.findCurrentIteration(project, date);
	}


	@Override
	public List<Iteration> findByProject(Project project) {
		return IterationRepository.findByProject(project);
		
	}
	
	@Override
	public Iterable<Iteration> createBatch(Iterable<Iteration> iterationBatch) {
		return IterationRepository.save(iterationBatch);
	}


	@Override
	public List<Iteration> findByProjectAndFetchWorkitemsEagerly(
			Project project) {
		return IterationRepository.findByProjectAndFetchWorkitemsEagerly(project);
	}
	




}
