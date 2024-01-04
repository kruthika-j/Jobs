package com.dto;

import java.sql.Date;

public class JobListDTO {
     private String Title;
    private String Description;
    private String Requirements;
    private Date Deadline;
    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
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
    
}
