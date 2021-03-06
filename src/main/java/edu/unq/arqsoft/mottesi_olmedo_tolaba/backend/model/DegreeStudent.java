package edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "degrees_students")
public class DegreeStudent extends PersistenceEntity {

	private static final long serialVersionUID = 6591632026675920348L;

	@OneToOne(cascade = CascadeType.ALL)	
	private Student student;
	
	@OneToOne(cascade = CascadeType.ALL)	
	private Degree degree;

	public DegreeStudent() {
	}

	public DegreeStudent(Student student, Degree degree) {
		this.student = student;
		this.degree = degree;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}

}
