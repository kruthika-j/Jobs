package com.kiruthika.job;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kiruthika.job.Entity.JobList;
import com.kiruthika.job.Entity.Resume;
import com.kiruthika.job.Entity.UserData;
import com.kiruthika.job.Entity.JobApplication;
import com.kiruthika.job.Service.JobApplicationService;
import com.kiruthika.job.Service.JobListingService;
import com.kiruthika.job.Service.RegistrationService;
import com.kiruthika.job.Service.ResumeService;

@RestController
public class JobApplicationController {
    private RegistrationService registrationService;
    private JobListingService jobListingService;
    private ResumeService resumeManagementService;
    private JobApplicationService jobApplicationService;

    @Autowired
    public JobApplicationController(RegistrationService registrationService, JobListingService jobListingService, ResumeService resumeManagementService, JobApplicationService jobApplicationService) {
        this.registrationService = registrationService;
        this.jobListingService = jobListingService;
        this.resumeManagementService = resumeManagementService;
        this.jobApplicationService = jobApplicationService;
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

    @DeleteMapping("/users/delete/{userId}")

    public String deleteUser(@PathVariable Long userId){
        try {
            registrationService.deleteUser(userId);
            return "User removed";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
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

    @DeleteMapping("/jobs/delete/{jobId}")
    public String deleteJob(@PathVariable Long jobId) {
        jobListingService.deleteJob(jobId);
        return "Success";
    }

    @DeleteMapping("jobs/deleteAll")
    public String deleteAllJobs(){
        jobListingService.deleteAllJobs();
        return "All jobs deleted";
    }

    @GetMapping("/resumes")
    public ResponseEntity<List<Resume>> getAllResumes(){
        List<Resume> resumes = resumeManagementService.getAllResumes();
        return ResponseEntity.status(HttpStatus.OK).body(resumes);
    }

    @GetMapping("/resumes/{jobSeekerId}")
    public ResponseEntity<List<Resume>> getResumeByUserId(@PathVariable Long jobSeekerId) {
        List<Resume> resumes = resumeManagementService.getResumeById(jobSeekerId);
        return ResponseEntity.status(HttpStatus.OK).body(resumes);
    }

    @PostMapping("post-resumes")
    public ResponseEntity<Object> postResumes(
            @RequestPart("resumeManagement") Resume resumeManagement,
            @RequestPart("resumeFile") MultipartFile resumeFile) {

        try {
            resumeManagement.setResumeFile(resumeFile.getBytes());
            Resume postedResume = resumeManagementService.postResumes(resumeManagement);
            return ResponseEntity.status(HttpStatus.CREATED).body(postedResume);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
        }
    }

    @DeleteMapping("resumes/delete/{jobSeekerId}")
    public String deleteResumeById(@PathVariable Long jobSeekerId){
        resumeManagementService.deleteAllResumesByJobSeekerId(jobSeekerId);
        return "resumes deleted";
    }
    @GetMapping("application/{applicationId}")
    public ResponseEntity<Object> getApplication(@PathVariable Long applicationId){
        JobApplication jobApplication = jobApplicationService.getApplication(applicationId);
        return ResponseEntity.status(HttpStatus.OK).body(jobApplication);
    }

    @GetMapping("application/jobSeeker/{jobSeekerId}")
    public ResponseEntity<List<JobApplication>> getApplicationByJobSeekerId(@PathVariable Long jobSeekerId) {
        List<Resume> applications = jobApplicationService.getApplicationById(jobSeekerId);
        return ResponseEntity.status(HttpStatus.OK).body(applications);
    }

}


    

