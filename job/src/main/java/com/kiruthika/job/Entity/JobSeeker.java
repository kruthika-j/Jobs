package com.kiruthika.job.Entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "jobseeker")
public class JobSeeker {
    @Id
    @Pattern(regexp = "^[a-z|A-Z|0-9]{6,12}$",
            message = "username must be of 6 to 12 length with no special characters")
    private String juname;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.\\-_*])([a-zA-Z0-9@#$%^&+=*.\\-_]){3,}$",  
        message = "enter valid password")
    private String Password;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "enter valid mail")
    private String email;
    private String Name;

    @Pattern(regexp ="^[6-9]\\d{9}",
            message = "enter valid contact number")
    private String Contact;

    private Date DOB;
    private String Qualification;
    private String Location;
    

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getDOB() {
        return DOB;
    }
    public void setDOB(Date dOB) {
        DOB = dOB;
    }
    public String getLocation() {
        return Location;
    }
    public void setLocation(String location) {
        Location = location;
    }
    public String getJuname() {
        return juname;
    }
    public void setJuname(String juname) {
        this.juname = juname;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
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
    public String getQualification() {
        return Qualification;
    }
    public void setQualification(String qualification) {
        Qualification = qualification;
    }
    public JobSeeker() {
    }
    public JobSeeker(String juname, String password, String email, String name, String contact, Date dOB,
            String qualification, String location) {
        this.juname = juname;
        this.Password = password;
        this.email = email;
        this.Name = name;
        this.Contact = contact;
        this.DOB = dOB;
        this.Qualification = qualification;
        this.Location = location;
    }
   
}