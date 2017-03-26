package com.wpm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the tasklog database table.
 * 
 */
@Entity
@NamedQuery(name="Tasklog.findAll", query="SELECT t FROM Tasklog t")
public class Tasklog implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TasklogPK id;

	private BigDecimal hoursRemaining;

	//bi-directional many-to-one association to Task
	@ManyToOne
	@JoinColumn(name="idTask", insertable=false, updatable=false)
	private Task task;

	public Tasklog() {
	}
	
	public Tasklog(TasklogPK id) {
		this.id = id;
	}

	public TasklogPK getId() {
		return this.id;
	}

	public void setId(TasklogPK id) {
		this.id = id;
	}

	public BigDecimal getHoursRemaining() {
		return this.hoursRemaining;
	}

	public void setHoursRemaining(BigDecimal hoursRemaining) {
		this.hoursRemaining = hoursRemaining;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}