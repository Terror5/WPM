package com.wpm.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tasklog database table.
 * 
 */
@Embeddable
public class TasklogPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idTask;

	@Temporal(TemporalType.DATE)
	private java.util.Date dayIteration;

	public TasklogPK() {
	}
	
	public TasklogPK(int idTask,java.util.Date dayIteration) {
		this.idTask = idTask;
		this.dayIteration = dayIteration;
	}
	
	public int getIdTask() {
		return this.idTask;
	}
	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}
	public java.util.Date getDayIteration() {
		return this.dayIteration;
	}
	public void setDayIteration(java.util.Date dayIteration) {
		this.dayIteration = dayIteration;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TasklogPK)) {
			return false;
		}
		TasklogPK castOther = (TasklogPK)other;
		return 
			(this.idTask == castOther.idTask)
			&& this.dayIteration.equals(castOther.dayIteration);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idTask;
		hash = hash * prime + this.dayIteration.hashCode();
		
		return hash;
	}
}