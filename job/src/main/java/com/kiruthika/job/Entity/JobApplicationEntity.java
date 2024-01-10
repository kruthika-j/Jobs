package com.kiruthika.job.Entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class JobApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;
    
    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private UserData jobSeekerId;
    
    @ManyToOne 
    @JoinColumn(name = "jobId",nullable = false)
    private JobList jobId;

    private Date applicationDate;

    public Long getApplicationId() {
        return applicationId;
    }
    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
    public UserData getJobSeekerId() {
        return jobSeekerId;
    }
    public void setJobSeekerId(UserData jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }
    public JobList getJobId() {
        return jobId;
    }
    public void setJobId(JobList jobId) {
        this.jobId = jobId;
    }
    public Date getApplicationDate() {
        return applicationDate;
    }
    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }
    public JobApplicationEntity() {
    }
    public JobApplicationEntity(Long applicationId, UserData jobSeekerId, JobList jobId, Date applicationDate) {
        this.applicationId = applicationId;
        this.jobSeekerId = jobSeekerId;
        this.jobId = jobId;
        this.applicationDate = applicationDate;
    }
}
