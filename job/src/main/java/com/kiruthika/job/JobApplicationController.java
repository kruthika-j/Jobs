package com.kiruthika.job;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UserDataDTO;
import com.kiruthika.job.Entity.JobApplication;
import com.kiruthika.job.Entity.JobList;
import com.kiruthika.job.Entity.UserData;
import com.kiruthika.job.Repository.JobListRepository;
import com.kiruthika.job.Service.JobListingService;
import com.kiruthika.job.Service.RegistrationService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class JobApplicationController {
    private RegistrationService registrationService;
    private JobListingService jobListingService;

    @Autowired
    public JobApplicationController(RegistrationService registrationService, JobListingService jobListingService) {
        this.registrationService = registrationService;
        this.jobListingService = jobListingService;

    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Object> showJobForm(@PathVariable Long userId) {
        UserData userData = registrationService.getUser(userId);
        if (userData != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userData);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserData>> getAllUsers() {
        List<UserData> allUsers = registrationService.getAllUsers();

        if (!allUsers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(allUsers);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @PostMapping("/create-users")
    public ResponseEntity<Object> createUser(@RequestBody UserData userData) {
        UserData createdUser = registrationService.createUser(userData);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobList>> getAllJobs() {
        List<JobList> jobList = jobListingService.getAllJobs();

        if (!jobList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(jobList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @PostMapping("/post-jobs")
    public ResponseEntity<Object> postJobs(@RequestBody JobList jobList) {
        JobList postedJob = jobListingService.postJobs(jobList);
        return ResponseEntity.status(HttpStatus.CREATED).body(postedJob);
    }

    @GetMapping("/jobs/employer/{employerId}")
    public ResponseEntity<List<JobList>> getJobs(@PathVariable Long employerId) {
        List<JobList> jobList = jobListingService.getJobs(employerId);
        return ResponseEntity.status(HttpStatus.OK).body(jobList);
    }

    @GetMapping("/jobs/{title}")
    public ResponseEntity<List<JobList>> getJobsByTitle(@PathVariable("title") String title) {
        List<JobList> jobList = jobListingService.getJobsByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(jobList);
    }


}
