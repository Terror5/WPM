package com.wpm.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wpm.exception.TaskNotFound;
import com.wpm.model.Iteration;
import com.wpm.model.SqlUser;
import com.wpm.model.Task;
import com.wpm.model.Tasklog;
import com.wpm.model.TasklogPK;
import com.wpm.repository.TaskRepository;
import com.wpm.repository.TasklogRepository;


@Service
public class TaskServiceImp implements TaskService {

	@Resource
	private TaskRepository taskRepository;
	
	@Resource
	private TasklogRepository tasklogRepository;
	

	@Transactional
	public Task create(Task task) {
		Task createdtask = task;
		return taskRepository.save(createdtask);

	}


	@Transactional(rollbackFor=TaskNotFound.class)
	public Task delete(int id) throws TaskNotFound {
		Task deletedTask = taskRepository.findOne(id);
		
		if(deletedTask == null){
			throw new TaskNotFound();
		}
		
		taskRepository.delete(deletedTask);
		return deletedTask;
	}


	@Transactional
	public List<Task> findAll() {
		return taskRepository.findAll();
	}


	@Transactional(rollbackFor=TaskNotFound.class)
	public Task update(Task task) throws TaskNotFound {
		Task updatedTask = taskRepository.findOne(task.getIdTask());
		
		if(updatedTask == null){
			throw new TaskNotFound();
		}
		
		updatedTask.setTitle(task.getTitle());
		updatedTask.setDescription(task.getDescription());
		updatedTask.setPriority(task.getPriority());
		updatedTask.setResolved(task.getResolved());
		updatedTask.setHoursPlanned(task.getHoursPlanned());
		updatedTask.setHoursWorked(task.getHoursWorked());
		updatedTask.setHoursRemaining(task.getHoursRemaining());
		// kommt AssignedTo auch hier rein?
		
		createUpdateTasklog(updatedTask, true);
		return updatedTask; 
	}


	@Transactional
	public Task findById(int id) {
		return taskRepository.findOne(id);
	}
	
	@Transactional
	public List<Task> findBytitel(String title){
		return taskRepository.findByTitle(title);
	}


	@Transactional
	public List<Task> findByUserAndterationInOrderByResolvedDesc(SqlUser user,
			List<Iteration> iterations) {
		return taskRepository.findByUserAndterationInOrderByResolvedDesc(user, iterations);
	}


	@Transactional
	public Iterable<Task> createBatch(Iterable<Task> taskBatch) {
		return taskRepository.save(taskBatch);
	}
	
	@Transactional
	public boolean createUpdateTasklog(Task task, boolean update){
		List<Tasklog> taskloglist = new ArrayList<Tasklog>();
		Date begin = task.getIteration().getStartIteration();
		Date end = task.getIteration().getEndIteration();
		
		Calendar from = Calendar.getInstance();
		from.setTime(begin);
		from = dateOnly(from);
		
		Calendar to = Calendar.getInstance();
		to.setTime(end);
		to = dateOnly(to);
		
		if(update == true){
			Calendar today = Calendar.getInstance();
			today = dateOnly(today);
			if(today.after(to)){ //Wenn heute nach Iterationsende ist -> nix machen
				return(true);
			}
			if (today.after(from)){ //Wenn heute in Iteration liegt -> ändere von heute ab die Tasks
				from = today;
			}	// Wenn heute vor der Iteration ist, den kompletten Log abändern
		}		
		
		while(!from.after(to)){
						
			TasklogPK id = new TasklogPK(task.getIdTask(),from.getTime());
			Tasklog tasklog = new Tasklog(id);
			tasklog.setHoursRemaining(task.getHoursRemaining());
			taskloglist.add(tasklog);
			
        	from.add(Calendar.DATE, 1);        	       	
		}
		
		tasklogRepository.save(taskloglist);
		return(true);
	}
	
	private Calendar dateOnly(Calendar cal){
    	cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
    	cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
    	cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
    	cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
		return cal;
	}


	@Transactional
	public List<Tasklog> findByIteration(Iteration iteration) {		
		return tasklogRepository.findByTaskIn(taskRepository.findByIteration(iteration));
	}

	

}
