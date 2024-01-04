package com.kiruthika.job.Entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class JobList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;
    private String Title;
    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private UserData employerId;
    private String Description;
    private String Requirements;
    private Date Deadline;


    public UserData getEmployerId() {
        return employerId;
    }

    public void setEmployerId(UserData employerId) {
        this.employerId = employerId;
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

    public JobList(Long jobId, String title, String description, String requirements, Date deadline) {
        this.jobId = jobId;
        Title = title;
        Description = description;
        Requirements = requirements;
        Deadline = deadline;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

}
