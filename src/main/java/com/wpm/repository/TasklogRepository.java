package com.wpm.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wpm.model.Task;
import com.wpm.model.Tasklog;
import com.wpm.model.TasklogPK;

public interface TasklogRepository extends JpaRepository<Tasklog, TasklogPK> {
	
	public List<Tasklog> findByTaskIn(Collection<Task> tasks);

}
