package com.wpm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the iteration database table.
 * 
 */
@Entity
@NamedQuery(name = "Iteration.findAll", query = "SELECT i FROM Iteration i")
public class Iteration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idIteration;

	@Temporal(TemporalType.DATE)
	private Date endIteration;

	private String evaluation;

	@Temporal(TemporalType.DATE)
	private Date startIteration;

	// bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name = "idProject")
	private Project project;

	// bi-directional many-to-one association to Task
	@OneToMany(mappedBy = "iteration")
	private List<Task> tasks;

	// bi-directional many-to-one association to Iteration
	@OneToMany(mappedBy = "iteration")
	private List<Workitem> workitems;

	public Iteration() {
	}

	public Iteration(int idIteration) {
		this.idIteration = idIteration;
	}

	public int getIdIteration() {
		return this.idIteration;
	}

	public void setIdIteration(int idIteration) {
		this.idIteration = idIteration;
	}

	public Date getEndIteration() {
		return this.endIteration;
	}

	public void setEndIteration(Date endIteration) {
		this.endIteration = endIteration;
	}

	public String getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public Date getStartIteration() {
		return this.startIteration;
	}

	public void setStartIteration(Date startIteration) {
		this.startIteration = startIteration;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task addTask(Task task) {
		getTasks().add(task);
		task.setIteration(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setIteration(null);

		return task;
	}

	public List<Workitem> getWorkitems() {
		return this.workitems;
	}

	public void setWorkitems(List<Workitem> workitems) {
		this.workitems = workitems;
	}

	public Workitem addWorkitem(Workitem workitem) {
		getWorkitems().add(workitem);
		workitem.setIteration(this);

		return workitem;
	}

	public Workitem removeWorkitem(Workitem workitem) {
		getWorkitems().remove(workitem);
		workitem.setIteration(null);

		return workitem;
	}

}