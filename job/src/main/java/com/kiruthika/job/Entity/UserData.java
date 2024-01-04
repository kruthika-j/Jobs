package com.kiruthika.job.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserData {
    private String name;
    private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserData() {
    }

    public UserData(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
