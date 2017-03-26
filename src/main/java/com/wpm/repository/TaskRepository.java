package com.wpm.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wpm.model.SqlUser;
import com.wpm.model.Task;
import com.wpm.model.Iteration;

public interface TaskRepository extends JpaRepository<Task,Integer>{
	

	public List<Task> findByTitle(String title);
	public List<Task> findByIteration(Iteration iteration);
	
	@Query("SELECT t FROM Task t WHERE t.user = :user AND t.iteration IN :iterations ORDER BY t.resolved DESC")
	public List<Task> findByUserAndterationInOrderByResolvedDesc(@Param("user")SqlUser user, @Param("iterations") List<Iteration> iterations);
	
	

}