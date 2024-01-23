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
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "resume_management")
public class Resume {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resumeId;

    @ManyToOne
    @JoinColumn(name = "juname",nullable = false)
    private JobSeeker juname;

    @Column(name = "filePath", columnDefinition = "BLOB")
    private byte[] filePath;

    @Pattern(regexp = "^[0-3]{1}[0-9]{1}[.-/][0-1]{1}[0-9]{1}[.-/][19|20][0-9]{3}")
    private Date updationDate;
    
    public Resume(Long resumeId, JobSeeker juname, byte[] filePath, Date updationDate) {
        this.resumeId = resumeId;
        this.juname = juname;
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
    public JobSeeker getJuname() {
        return juname;
    }
    public void setJuname(JobSeeker juname) {
        this.juname = juname;
    }
    
}
