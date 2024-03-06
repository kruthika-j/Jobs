package com.kiruthika.job.dto;

import java.sql.Date;

import com.kiruthika.job.Entity.JobSeeker;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

public class ResumeDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resumeId;

    @ManyToOne
    @JoinColumn(name = "juname",nullable = false)
    private JobSeeker juname;

    @Column(name = "file", columnDefinition = "BLOB")
    private byte[] file;

    @PrePersist
    private void onCreate() {
        this.updationDate = new java.sql.Date(System.currentTimeMillis());
    }
    private Date updationDate;
    
    public ResumeDTO(Long resumeId, JobSeeker juname, byte[] file, Date updationDate) {
        this.resumeId = resumeId;
        this.juname = juname;
        this.file = file;
        this.updationDate = updationDate;
    }
    public ResumeDTO() {
    }
    
    public byte[] getFile() {
        return file;
    }
    public void setFile(byte[] file) {
        this.file = file;
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
    public JobSeeker getJuname() {
        return juname;
    }
    public void setJuname(JobSeeker juname) {
        this.juname = juname;
    }
}
