package com.wpm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the workitem database table.
 * 
 */
@Entity
@NamedQuery(name="Workitem.findAll", query="SELECT w FROM Workitem w")
public class Workitem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idWorkitem;
	
	private String description;
	
	private byte priority;
	
	private byte sizeEstimate;
	
	private String title;
	
	private byte type;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="workitem")
	private List<Task> tasks;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="idProject")
	private Project project;

	//bi-directional many-to-one association to Iteration
	@ManyToOne
	@JoinColumn(name="targetIteration")
	private Iteration iteration;

	public Workitem() {
	}

	public Workitem(int idWorkitem) {
		this.idWorkitem = idWorkitem;
	}

	public int getIdWorkitem() {
		return this.idWorkitem;
	}

	public void setIdWorkitem(int idWorkitem) {
		this.idWorkitem = idWorkitem;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getPriority() {
		return this.priority;
	}

	public void setPriority(byte priority) {
		this.priority = priority;
	}

	public byte getSizeEstimate() {
		return this.sizeEstimate;
	}

	public void setSizeEstimate(byte sizeEstimate) {
		this.sizeEstimate = sizeEstimate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte getType() {
		return this.type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task addTask(Task task) {
		getTasks().add(task);
		task.setWorkitem(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setWorkitem(null);

		return task;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Iteration getIteration() {
		return this.iteration;
	}

	public void setIteration(Iteration iteration) {
		this.iteration = iteration;
	}

}