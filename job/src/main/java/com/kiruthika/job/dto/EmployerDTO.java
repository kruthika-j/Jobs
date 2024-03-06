package com.kiruthika.job.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class EmployerDTO {
    @Id
    @NotNull(message = "enter username")
    @Pattern(regexp = "^[a-z|A-Z|0-9|[@#$%^-_*]]{6,50}$", message = "username must be of 6 to 50 length with no special characters")
    private String uname;

    @Pattern(regexp = "^[A-Z]{1}[0-9]{5}[A-Z]{2}[0-9]{4}[A-Z]{3}[0-9]{6}$", message = "enter valid company id")
    private String companyId;

    private String companyName;

    @NotNull
    @Pattern(regexp = "^[6-9]\\d{9}", message = "enter valid contact number")
    @Column(length = 10)
    private String Contact;
    private String Location;
    private String Website;

    @NotNull
    private String Designation;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
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

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public EmployerDTO() {
    }

    public EmployerDTO(
            @NotNull(message = "enter username") @Pattern(regexp = "^[a-z|A-Z|0-9|[@#$%^-_*]]{6,50}$", message = "username must be of 6 to 50 length with no special characters") String uname,
            @Pattern(regexp = "^[A-Z]{1}[0-9]{5}[A-Z]{2}[0-9]{4}[A-Z]{3}[0-9]{6}$", message = "enter valid company id") String companyId,
            String companyName,
            @NotNull @Pattern(regexp = "^[6-9]\\d{9}", message = "enter valid contact number") String contact,
            String location, String website, @NotNull String designation) {
        this.uname = uname;
        this.companyId = companyId;
        this.companyName = companyName;
        Contact = contact;
        Location = location;
        Website = website;
        Designation = designation;
    }
}
