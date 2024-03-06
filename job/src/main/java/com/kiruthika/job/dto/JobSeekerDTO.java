package com.kiruthika.job.dto;

import java.sql.Date;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;

public class JobSeekerDTO {
     @Id
    @Pattern(regexp = "^[a-z|A-Z|0-9|[@#$%^-_*]]{6,50}$", message = "username must be of 6 to 50 length with no special characters")
    private String juname;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "enter valid mail")
    private String email;
    private String Name;

    @Pattern(regexp = "^[6-9]\\d{9}", message = "enter valid contact number")
    private String Contact;

    private Date DOB;
    private String Qualification;
    private String Location;

    public String getEmail() {
        return email;
    }

    public String getJuname() {
        return juname;
    }

    public void setJuname(String juname) {
        this.juname = juname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date dOB) {
        DOB = dOB;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public JobSeekerDTO() {
    }

    public JobSeekerDTO(
            @Pattern(regexp = "^[a-z|A-Z|0-9|[@#$%^-_*]]{6,50}$", message = "username must be of 6 to 50 length with no special characters") String juname,
            @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "enter valid mail") String email,
            String name, @Pattern(regexp = "^[6-9]\\d{9}", message = "enter valid contact number") String contact,
            Date dOB, String qualification, String location) {
        this.juname = juname;
        this.email = email;
        this.Name = name;
        this.Contact = contact;
        this.DOB = dOB;
        this.Qualification = qualification;
        this.Location = location;
    }

}
