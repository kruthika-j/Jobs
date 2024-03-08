package com.kiruthika.job.Entity;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "jobseeker")
public class JobSeeker {
    @Id
    @Pattern(regexp = "^[a-z|A-Z|0-9|[@#$%^-_*]]{6,50}$", message = "username must be of 6 to 50 length with no special characters")
    private String juname;

    @NotNull(message = "enter password")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.\\-_*])([a-zA-Z0-9@#$%^&+=*.\\-_]){4,255}$", message = "enter valid password")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "enter valid mail")
    private String email;
    private String Name;

    @Pattern(regexp = "^[6-9]\\d{9}", message = "enter valid contact number")
    private String Contact;

    private Date DOB;
    private String Qualification;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "location_id", referencedColumnName = "locationId", nullable = false)
    private Location location;

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.REMOVE)
    private List<Application> applications;

    @OneToMany(mappedBy = "juname", cascade = CascadeType.REMOVE)
    private List<Resume> resumes;

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

    public String getJuname() {
        return juname;
    }

    public void setJuname(String juname) {
        this.juname = juname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public JobSeeker(
            @Pattern(regexp = "^[a-z|A-Z|0-9|[@#$%^-_*]]{6,50}$", message = "username must be of 6 to 50 length with no special characters") String juname,
            @NotNull(message = "enter password") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.\\-_*])([a-zA-Z0-9@#$%^&+=*.\\-_]){4,255}$", message = "enter valid password") String password,
            @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "enter valid mail") String email,
            String name, @Pattern(regexp = "^[6-9]\\d{9}", message = "enter valid contact number") String contact,
            Date dOB, String qualification, Location location) {
        this.juname = juname;
        this.password = password;
        this.email = email;
        Name = name;
        Contact = contact;
        DOB = dOB;
        Qualification = qualification;
        this.location = location;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public List<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(List<Resume> resumes) {
        this.resumes = resumes;
    }

}