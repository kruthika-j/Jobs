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
    @JoinColumn(name = "uname",nullable = false)
    private JobSeeker j_uname;

    @Column(name = "filePath", columnDefinition = "BLOB")
    private byte[] filePath;

    private Date updationDate;
    
    public Resume(Long resumeId, JobSeeker j_uname, byte[] filePath, Date updationDate) {
        this.resumeId = resumeId;
        this.j_uname = j_uname;
        this.filePath = filePath;
        this.updationDate = updationDate;
    }
    public Resume() {
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
    public JobSeeker getJ_uname() {
        return j_uname;
    }
    public void setJ_uname(JobSeeker j_uname) {
        this.j_uname = j_uname;
    }
    
}
