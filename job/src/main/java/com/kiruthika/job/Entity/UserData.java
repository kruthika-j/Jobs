// package com.kiruthika.job.Entity;

// import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "user_data")
// public class UserData {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long userId;
//     private String name;
//     private String password;
//     @Enumerated(EnumType.STRING)
//     private Role role;

//     private enum Role {
//         EMPLOYER,
//         JOBSEEKER;
//     };

//     public Long getUserId() {
//         return userId;
//     }

//     public void setUserId(Long userId) {
//         this.userId = userId;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public String getPassword() {
//         return password;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }

//     public UserData() {
//     }

//     public UserData(String name, String password) {
//         this.name = name;
//         this.password = password;
//     }

//     public Role getRole() {
//         return role;
//     }

//     public void setRole(Role role) {
//         this.role = role;
//     }

//     public UserData save(UserData userData) {
//         return null;
//     }
// }
