package com.kiruthika.job.Entity;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_application")
public class JobApplicationEntity {
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


    private Date applicationDate;
    private Time applicationTime;

    @PrePersist
    private void onCreate() {
        this.applicationDate = new java.sql.Date(System.currentTimeMillis());
        this.applicationTime = new Time(System.currentTimeMillis());
    }


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
    public Date getApplicationDate() {
        return applicationDate;
    }
    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }
    public JobApplicationEntity() {
    }
    
    public Time getApplicationTime() {
        return applicationTime;
    }
    public void setApplicationTime(Time applicationTime) {
        this.applicationTime = applicationTime;
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


    public JobApplicationEntity(Long applicationId, JobSeeker jobSeeker, JobList jobList, byte[] file,
            Date applicationDate, Time applicationTime) {
        this.applicationId = applicationId;
        this.jobSeeker = jobSeeker;
        this.jobList = jobList;
        this.file = file;
        this.applicationDate = applicationDate;
        this.applicationTime = applicationTime;
    }

   
}
