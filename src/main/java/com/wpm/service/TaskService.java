package com.wpm.service;

import java.util.List;

import com.wpm.exception.TaskNotFound;
import com.wpm.model.Iteration;
import com.wpm.model.SqlUser;
import com.wpm.model.Task;
import com.wpm.model.Tasklog;

public interface TaskService {
        
        public Task create(Task task);
        public Task delete(int id) throws TaskNotFound;
        public List<Task> findAll();
        public Task update(Task task) throws TaskNotFound;
        public Task findById(int id);
        public Iterable<Task> createBatch(Iterable<Task> taskBatch);        
      
        public List<Task> findBytitel(String titel);
        public List<Task> findByUserAndterationInOrderByResolvedDesc(SqlUser user, List<Iteration> iterations);
        
        //Tasklog
    	public boolean createUpdateTasklog(Task task, boolean update);
        public List<Tasklog> findByIteration(Iteration iteration);  
}
