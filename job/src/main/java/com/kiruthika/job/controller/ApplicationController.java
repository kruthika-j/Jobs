package com.kiruthika.job.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kiruthika.job.Entity.Application;
import com.kiruthika.job.Service.ApplicationService;

@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService jobApplicationService;

    @GetMapping("/employer/job/view-application/{applicationId}")
    public ResponseEntity<Object> getApplication(@PathVariable Long applicationId) {
        try {
            Application jobApplication = jobApplicationService.getApplication(applicationId);

            if (jobApplication != null) {
                return ResponseEntity.status(HttpStatus.OK).body(jobApplication);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("JobApplication with ID " + applicationId + " not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
            // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error
            // retrieving JobApplication!");
        }
    }

    @GetMapping("/jobSeeker/jobs-applied")
    public ResponseEntity<List<Application>> getApplicationByJobSeekerJuname()
            throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String juname = authentication.getName();
        List<Application> applications = jobApplicationService.getApplicationByJuname(juname);
        return ResponseEntity.status(HttpStatus.OK).body(applications);
    }

    @GetMapping("/employer/job/view-application/by-job-id/{jobId}")
    public ResponseEntity<List<Application>> getApplicationByJobId(@PathVariable Long jobId) {
        List<Application> applications = jobApplicationService.getApplicationByJobId(jobId);
        return ResponseEntity.status(HttpStatus.OK).body(applications);
    }

    @PostMapping("/jobSeeker/job/apply")
    public ResponseEntity<Object> applyForJob(@RequestBody Application request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String juname = authentication.getName();
            jobApplicationService.applyForJob(juname, request.getJobList());
            return ResponseEntity.status(HttpStatus.CREATED).body("Job application submitted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error applying for the job: " + e.getMessage());
        }
    }
}
