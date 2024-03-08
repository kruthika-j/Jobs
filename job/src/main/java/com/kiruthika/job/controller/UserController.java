package com.kiruthika.job.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kiruthika.job.Entity.AuthRequest;
import com.kiruthika.job.Entity.Employer;
import com.kiruthika.job.Entity.JobSeeker;
import com.kiruthika.job.Service.EmployerService;
import com.kiruthika.job.Service.JobSeekerService;
import com.kiruthika.job.Service.JwtService;
//import com.kiruthika.job.dto.EmployerDTO;
//import com.kiruthika.job.dto.JobSeekerDTO;

import jakarta.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private EmployerService employerService;
    @Autowired
    private JobSeekerService jobSeekerService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    // Employer signup
    @PostMapping("/register/employer")
    public ResponseEntity<Object> createEmployer(@Valid @RequestBody Employer employer)
            throws HttpMessageNotReadableException {
        employerService.createEmployer(employer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registered successfully");
    }

    // jobseeker signup
    @PostMapping("/register/jobseeker")
    public ResponseEntity<Object> createJobSeeker(@Valid @RequestBody JobSeeker jobSeeker)
            throws HttpMessageNotReadableException {
        JobSeeker newJobSeeker = jobSeekerService.createJobSeeker(jobSeeker);
        return ResponseEntity.status(HttpStatus.CREATED).body(newJobSeeker);
    }

    // sign in
    @PostMapping("/signIn/employer")
    public String authenticateAndGetTokenEmployer(@RequestBody AuthRequest authRequest) {

        // System.out.println("Authenticating user: " + authRequest.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String roles = "EMPLOYER";
            return jwtService.generateToken(authRequest.getUsername(), roles);
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @PostMapping("/signIn/jobSeeker")
    public String authenticateAndGetTokenJobSeeker(@RequestBody AuthRequest authRequest) {

        // System.out.println("Authenticating user: " + authRequest.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String roles = "JOB_SEEKER";
            return jwtService.generateToken(authRequest.getUsername(), roles);

        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @GetMapping("/employer/profile")
    public ResponseEntity<Object> getEmployer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uname = authentication.getName();
        Employer employer = employerService.getEmployer(uname);

        if (employer != null) {
            // EmployerDTO employerDTO = convertToDTO(employer);
            return ResponseEntity.status(HttpStatus.OK).body(employer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employer not found");
        }
    }

    // jobseeker details
    @GetMapping("/jobSeeker/profile")
    public ResponseEntity<Object> getjobSeeker() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String juname = authentication.getName();
        JobSeeker jobSeeker = jobSeekerService.getJobSeeker(juname);

        if (jobSeeker != null) {
            //JobSeekerDTO jobSeekerDTO = convertToDTO(jobSeeker);
            return ResponseEntity.status(HttpStatus.OK).body(jobSeeker);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("JobSeeker not found");
        }
    }    

    // delete employer by username
    @DeleteMapping("/employer/delete")
    public String deleteUser(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            String uname = authentication.getName();
            employerService.deleteUser(uname);
            return "User removed";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // delete jobseeker by juname
    @DeleteMapping("/jobSeeker/delete")
    public String deleteJobSeeker() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String juname = authentication.getName();
        jobSeekerService.deleteJobSeeker(juname);
        return "User Deleted";
    }

}
