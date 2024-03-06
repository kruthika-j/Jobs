package com.kiruthika.job.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;
    
    @ManyToOne
    @JoinColumn(name = "jobSeeker",nullable = false)
    private JobSeeker jobSeeker;
    
    @ManyToOne 
    @JoinColumn(name = "jobList",nullable = false)
    private JobList jobList;

    @Column(name = "file", columnDefinition = "BLOB")
    private byte[] file;

    private LocalDateTime AppliedAt;

    public Long getApplicationId() {
        return applicationId;
    }
    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
    public JobList getJobList() {
        return jobList;
    }
    public void setJobList(JobList jobList) {
        this.jobList = jobList;
    }
    public Application() {
    }
    
    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }
    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }
    public byte[] getFile() {
        return file;
    }
    public void setFile(byte[] file) {
        this.file = file;
    }
    public LocalDateTime getAppliedAt() {
        return AppliedAt;
    }
    public void setAppliedAt(LocalDateTime appliedAt) {
        AppliedAt = appliedAt;
    }
    public Application(Long applicationId, JobSeeker jobSeeker, JobList jobList, byte[] file,
            LocalDateTime appliedAt) {
        this.applicationId = applicationId;
        this.jobSeeker = jobSeeker;
        this.jobList = jobList;
        this.file = file;
        this.AppliedAt = appliedAt;
    } 
}
