package com.kiruthika.job.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "employer")
public class Employer {

    @Id
    @NotNull(message = "enter username")
    @Pattern(regexp = "^[a-z|A-Z|0-9|[@#$%^-_*]]{6,50}$", message = "username must be of 6 to 50 length with no special characters")
    private String uname;

    @NotNull(message = "enter password")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.\\-_*])([a-zA-Z0-9@#$%^&+=*.\\-_]){4,255}$", message = "enter valid password")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @Pattern(regexp = "^[A-Z]{1}[0-9]{5}[A-Z]{2}[0-9]{4}[A-Z]{3}[0-9]{6}$", message = "enter valid company id")
    private String companyId;

    private String companyName;

    @NotNull
    @Pattern(regexp = "^[6-9]\\d{9}", message = "enter valid contact number")
    @Column(length = 10)
    private String Contact;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "location_id", referencedColumnName = "locationId", nullable = false)
    private Location location;

    private String Website;

    @NotNull
    private String Designation;

    
    @OneToMany(mappedBy = "uname", cascade = CascadeType.REMOVE)
    private List<JobList> jobList;


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

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Employer(
            @NotNull(message = "enter username") @Pattern(regexp = "^[a-z|A-Z|0-9|[@#$%^-_*]]{6,50}$", message = "username must be of 6 to 50 length with no special characters") String uname,
            @NotNull(message = "enter password") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.\\-_*])([a-zA-Z0-9@#$%^&+=*.\\-_]){4,255}$", message = "enter valid password") String password,
            @Pattern(regexp = "^[A-Z]{1}[0-9]{5}[A-Z]{2}[0-9]{4}[A-Z]{3}[0-9]{6}$", message = "enter valid company id") String companyId,
            String companyName,
            @NotNull @Pattern(regexp = "^[6-9]\\d{9}", message = "enter valid contact number") String contact,
            Location location, String website, @NotNull String designation) {
        this.uname = uname;
        this.password = password;
        this.companyId = companyId;
        this.companyName = companyName;
        Contact = contact;
        this.location = location;
        Website = website;
        Designation = designation;
    }

    public Employer() {
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }
}
