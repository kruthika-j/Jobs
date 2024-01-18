package com.kiruthika.job.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="employer")
public class Employer {
    @Id
    private String uname;

    private String password;
    private String companyId;
    private String companyName;
    private String Contact;
    private String Location;
    private String Website;

    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getCompanyId() {
        return companyId;
    }
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getContact() {
        return Contact;
    }
    public void setContact(String contact) {
        Contact = contact;
    }
    public String getLocation() {
        return Location;
    }
    public void setLocation(String location) {
        Location = location;
    }
    public String getWebsite() {
        return Website;
    }
    public void setWebsite(String website) {
        Website = website;
    }
    public Employer() {
    }
    public Employer(String uname, String password, String companyId, String companyName, String contact,
            String location, String website) {
        this.uname = uname;
        this.password = password;
        this.companyId = companyId;
        this.companyName = companyName;
        Contact = contact;
        Location = location;
        Website = website;
    }
}
