package com.kiruthika.job.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jobseeker")
public class JobSeeker {
    @Id
    private String j_uname;
    private String Password;
    private String Name;
    private String Contact;
    private String Qualification;
    
    public String getJ_uname() {
        return j_uname;
    }
    public void setJ_uname(String j_uname) {
        this.j_uname = j_uname;
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
    public JobSeeker(String j_uname, String password, String name, String contact, String qualification) {
        this.j_uname = j_uname;
        this.Password = password;
        this.Name = name;
        this.Contact = contact;
        this.Qualification = qualification;
    }
}
