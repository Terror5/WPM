package com.wpm.service;

import java.util.List;

import com.wpm.exception.WorkitemNotFound;
import com.wpm.model.Iteration;
import com.wpm.model.Project;
import com.wpm.model.Workitem;

public interface WorkitemService {
	
    public Workitem create(Workitem workitem);
    public Workitem delete(int id) throws WorkitemNotFound;
    public List<Workitem> findAll();
    public Workitem update(Workitem workitem) throws WorkitemNotFound;
    public Workitem findById(int id);
    
    public List<Workitem> findByProject(Project project);
    public List<Workitem> findBytitel(String titel);
    public List<Workitem> findByProjectAndIteration(Project project, Iteration iteration);
    public List<Workitem> findByProjectAndIterationAndFetchTasksEagerly(Project project, Iteration iteration, byte type);
    public List<Workitem> findAllAndFetchTasksEagerly();
    public List<Workitem> findByProjectAndTypeNot(Project project, byte type);    
    public List<Workitem> findByProjectAndType(Project project, byte type);
    public List<Workitem> findAllAndFetchTasksEagerlyByProjectAndIteration(Project project,Iteration iteration);

}
