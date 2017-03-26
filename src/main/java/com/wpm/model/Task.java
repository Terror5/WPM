package com.wpm.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the task database table.
 * 
 */
@Entity
@NamedQuery(name="Task.findAll", query="SELECT t FROM Task t")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idTask;

	private String description;

	private BigDecimal hoursPlanned;

	private BigDecimal hoursRemaining;

	private BigDecimal hoursWorked;

	private byte priority;
	
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean resolved;

	private String title;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="AssignedTo")
	private SqlUser user;

	//bi-directional many-to-one association to Iteration
	@ManyToOne
	@JoinColumn(name="idIteration")
	private Iteration iteration;

	//bi-directional many-to-one association to Task
	@ManyToOne
	@JoinColumn(name="motherTask")
	private Task task;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="task")
	private List<Task> tasks;

	//bi-directional many-to-one association to Workitem
	@ManyToOne
	@JoinColumn(name="idWorkitem")
	private Workitem workitem;
	
	//bi-directional many-to-one association to Tasklog
	@OneToMany(mappedBy="task")
	private List<Tasklog> tasklogs;

	public Task() {
	}
	
	public Task(Workitem workitem, Iteration iteration) {
		this.iteration = iteration;
		this.workitem = workitem;
	}

	public int getIdTask() {
		return this.idTask;
	}

	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getHoursPlanned() {
		return this.hoursPlanned;
	}

	public void setHoursPlanned(BigDecimal hoursPlanned) {
		this.hoursPlanned = hoursPlanned;
	}

	public BigDecimal getHoursRemaining() {
		return this.hoursRemaining;
	}

	public void setHoursRemaining(BigDecimal hoursRemaining) {
		this.hoursRemaining = hoursRemaining;
	}

	public BigDecimal getHoursWorked() {
		return this.hoursWorked;
	}

	public void setHoursWorked(BigDecimal hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public byte getPriority() {
		return this.priority;
	}

	public void setPriority(byte priority) {
		this.priority = priority;
	}

	public boolean getResolved() {
		return this.resolved;
	}

	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public SqlUser getUser() {
		return this.user;
	}

	public void setUser(SqlUser user) {
		this.user = user;
	}

	public Iteration getIteration() {
		return this.iteration;
	}

	public void setIteration(Iteration iteration) {
		this.iteration = iteration;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task addTask(Task task) {
		getTasks().add(task);
		task.setTask(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setTask(null);

		return task;
	}

	public Workitem getWorkitem() {
		return this.workitem;
	}

	public void setWorkitem(Workitem workitem) {
		this.workitem = workitem;
	}
	
	public List<Tasklog> getTasklogs() {
		return this.tasklogs;
	}

	public void setTasklogs(List<Tasklog> tasklogs) {
		this.tasklogs = tasklogs;
	}

	public Tasklog addTasklog(Tasklog tasklog) {
		getTasklogs().add(tasklog);
		tasklog.setTask(this);

		return tasklog;
	}

	public Tasklog removeTasklog(Tasklog tasklog) {
		getTasklogs().remove(tasklog);
		tasklog.setTask(null);

		return tasklog;
	}

}