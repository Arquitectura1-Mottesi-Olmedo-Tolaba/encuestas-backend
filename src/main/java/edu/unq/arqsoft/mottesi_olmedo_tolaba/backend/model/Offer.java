package edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.model;


import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;

@Entity
@Table(name = "offers")
public class Offer extends PersistenceEntity {

	@OneToOne(cascade = CascadeType.ALL)
    private Subject subject;
    
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
    private List<Course> courses;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
    private List<Option> options;
        
    public Offer() {
    }

    public Offer(Subject subject, List<Course> courses) {
        this.subject = subject;
        this.courses = courses;
    }

    public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public Option getFirstOption() {
		return this.options.get(0);
	}

}