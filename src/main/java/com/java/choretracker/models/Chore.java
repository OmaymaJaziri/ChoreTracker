package com.java.choretracker.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "chores")
public class Chore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Title is required!")
	@Size(min = 3, message = "Title must be at least 3 characters")
	private String title;

	@NotEmpty(message = "Description is required!")
	@Size(min = 10, message = "Description must be at least 10 characters")
	private String description;

	@NotEmpty(message = "location is required!")
	private String location;

	@Column(updatable = false)
	@DateTimeFormat(pattern="MM/dd/yyyy")	
	private Date createdAt;
	@DateTimeFormat(pattern="MM/dd/yyyy")	
	private Date updatedAt;

	

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User employer;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="join_id")
    private User employee;

	public Chore() {
	}
	
	
	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public User getEmployer() {
		return employer;
	}
	public void setEmployer(User employer) {
		this.employer = employer;
	}
	public User getEmployee() {
		return employee;
	}
	public void setEmployee(User employee) {
		this.employee = employee;
	}






	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    } 
	
	
	
}
