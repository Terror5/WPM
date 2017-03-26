package com.wpm.service;

import java.util.Date;
import java.util.List;


import com.wpm.exception.IterationNotFound;
import com.wpm.model.Iteration;
import com.wpm.model.Project;

public interface IterationService {
	
    public Iteration create(Iteration iteration);
    public Iteration delete(int id) throws IterationNotFound;
    public List<Iteration> findAll();
    public Iteration update(Iteration iteration) throws IterationNotFound;
    public Iteration findById(int id);
    public Iterable<Iteration> createBatch(Iterable<Iteration> iterationBatch);
    
    public List<Iteration> findByProject(Project project);
    public List<Iteration> findBytitel(String titel);
    public Iteration findCurrentIteration(Project project, Date date);
    public List<Iteration> findByProjectAndFetchWorkitemsEagerly(Project project);

}
