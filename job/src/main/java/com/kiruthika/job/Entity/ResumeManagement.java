package com.kiruthika.job.Entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class ResumeManagement {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resumeId;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private UserData jobSeekerId;
    
    private String filePath;
    private Date updationDate;
    
    public ResumeManagement() {
    }
   
    public ResumeManagement(Long resumeId, UserData jobSeekerId, String filePath, Date updationDate) {
        this.resumeId = resumeId;
        this.jobSeekerId = jobSeekerId;
        this.filePath = filePath;
        this.updationDate = updationDate;
    }

    public UserData getJobSeekerId() {
        return jobSeekerId;
    }
    public void setJobSeekerId(UserData jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public Date getUpdationDate() {
        return updationDate;
    }
    public void setUpdationDate(Date updationDate) {
        this.updationDate = updationDate;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }
    
}
