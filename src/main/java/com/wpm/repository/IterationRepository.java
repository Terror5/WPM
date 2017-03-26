package com.wpm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wpm.model.Iteration;
import com.wpm.model.Project;

public interface IterationRepository extends JpaRepository<Iteration,Integer>{
	
	@Query("SELECT i FROM Iteration i  WHERE i.project = :project AND i.startIteration <= :date AND i.endIteration >= :date")
	public Iteration findCurrentIteration(@Param("project")Project project, @Param("date")Date date);
	
    @Query("SELECT distinct i FROM Iteration i LEFT JOIN FETCH i.workitems w WHERE i.project = :project")
    public List<Iteration> findByProjectAndFetchWorkitemsEagerly(@Param("project")Project project);
	
	public List<Iteration> findByProject(Project project);

}
