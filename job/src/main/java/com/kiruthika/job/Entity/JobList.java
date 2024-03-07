package com.kiruthika.job.Entity;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
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
    private LocalDateTime postedDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id", referencedColumnName = "categoryId", nullable = false)
    private Category category;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "location_id", referencedColumnName = "locationId", nullable = false)
    private Location location;  

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
  
   

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public LocalDateTime getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDateTime postedDate) {
        this.postedDate = postedDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public JobList(Long jobId, String title, Employer uname, String description, String requirements, Date deadline,
            LocalDateTime postedDate, Category category, Location location) {
        this.jobId = jobId;
        this.title = title;
        this.uname = uname;
        this.Description = description;
        this.Requirements = requirements;
        this.Deadline = deadline;
        this.postedDate = postedDate;
        this.category = category;
        this.location = location;
    }

    


}
