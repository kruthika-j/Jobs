package com.kiruthika.job.Entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class JobList {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String Title;
    private String Description;
    private String Requirements;
    private Date Deadline;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public String getRequirements() {
        return Requirements;
    }
    public void setRequirements(String requirements) {
        Requirements = requirements;
    }
    public Date getDeadline() {
        return Deadline;
    }
    public void setDeadline(Date deadline) {
        Deadline = deadline;
    }
    public JobList() {
    }
    public JobList(Long id, String title, String description, String requirements, Date deadline) {
        this.id = id;
        Title = title;
        Description = description;
        Requirements = requirements;
        Deadline = deadline;
    }

}
