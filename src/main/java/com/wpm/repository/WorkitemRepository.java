package com.wpm.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wpm.model.Iteration;
import com.wpm.model.Project;
import com.wpm.model.Workitem;

public interface WorkitemRepository extends JpaRepository<Workitem,Integer>{
	

	public List<Workitem> findByTitle(String title);
	public List<Workitem> findByProjectAndIteration(Project project, Iteration iteration);
	
    @Query("SELECT w FROM Workitem w LEFT JOIN FETCH w.tasks WHERE w.project = (:project) AND w.iteration = (:iteration) AND w.type <> (:type)")
    public List<Workitem> findByProjectAndIterationAndFetchTasksEagerly(@Param("project") Project project, 
    																	@Param("iteration") Iteration iteration, @Param("type") byte type);
    
    @Query("SELECT w FROM Workitem w LEFT JOIN FETCH w.tasks")
    public List<Workitem> findAllAndFetchTasksEagerly();
    
    @Query("SELECT distinct w FROM Workitem w LEFT JOIN FETCH w.tasks WHERE w.project = (:project) AND w.iteration = (:iteration)")
    public List<Workitem> findAllAndFetchTasksEagerlyByProjectAndIteration(@Param("project") Project project,@Param("iteration") Iteration iteration);
    
    public List<Workitem> findByProject(Project project);
    
    //Für alle Workitems ohne High-Level-Workitems
    public List<Workitem> findByProjectAndTypeNot(Project project, byte type);
    
    //Für alle High-Level-Workitems => Project Plan
    public List<Workitem> findByProjectAndType(Project project, byte type);

}


