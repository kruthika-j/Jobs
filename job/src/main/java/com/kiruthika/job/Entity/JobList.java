package com.kiruthika.job.Entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_list")
public class JobList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    private String title;
    @ManyToOne
    @JoinColumn(name = "uname",referencedColumnName = "uname", nullable = false)
    private Employer uname;
    private String Description;
    private String Requirements;
    private Date Deadline;
    private Date PostedDate;
    private String category;
    private String Location;
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Employer getUname() {
        return uname;
    }
    
    public void setUname(Employer uname) {
        this.uname = uname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
  
    public JobList(Long jobId, String title, Employer uname, String description, String requirements,
            Date deadline, Date postedDate,String category,String Location) {
        this.jobId = jobId;
        this.title = title;
        this.uname = uname;
        this.Description = description;
        this.Requirements = requirements;
        this.Deadline = deadline;
        this.PostedDate = postedDate;
        this.category = category;
        this.Location = Location;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Date getPostedDate() {
        return PostedDate;
    }

    public void setPostedDate(Date postedDate) {
        PostedDate = postedDate;
    }
}
