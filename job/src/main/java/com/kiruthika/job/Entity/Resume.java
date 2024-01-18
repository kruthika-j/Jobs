package com.kiruthika.job.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "resume_management")
public class Resume {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resumeId;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private UserData jobSeekerId;

    @Column(name = "filePath", columnDefinition = "BLOB")
    private byte[] filePath;

    private Date updationDate;
    
    public Resume(Long resumeId, UserData jobSeekerId, byte[] filePath, Date updationDate) {
        this.resumeId = resumeId;
        this.jobSeekerId = jobSeekerId;
        this.filePath = filePath;
        this.updationDate = updationDate;
    }
    public Resume() {
    }
    public UserData getJobSeekerId() {
        return jobSeekerId;
    }
    public void setJobSeekerId(UserData jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }
    public byte[] getFilePath() {
        return filePath;
    }
    public void setFilePath(byte[] filePath) {
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
