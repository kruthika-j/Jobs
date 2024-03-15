package com.kiruthika.job.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kiruthika.job.Entity.JobList;
import com.kiruthika.job.Service.JobListService;

@RestController
public class JobController {

    @Autowired
    private JobListService jobListingService;

    // jobs display in jobseeker login
    @GetMapping("/jobSeeker/jobs")
    public ResponseEntity<List<JobList>> getAllJobs() {
        List<JobList> jobs = jobListingService.getAllJobs();

        if (!jobs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(jobs);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @GetMapping("/jobSeeker/jobs/my-location")
    public List<JobList> getJobsInMyLocation() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uname = authentication.getName();
        List<JobList> jobs = jobListingService.getJobsInMyLocation(uname);
        return jobs;
    }
    
    
    @GetMapping("/jobSeeker/jobs/today")
    public ResponseEntity<List<JobList>> getJobsPostedToday() {
        List<JobList> jobsToday = jobListingService.getJobsPostedToday();

        if (!jobsToday.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(jobsToday);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @GetMapping("/jobSeeker/jobs/this-week")
    public ResponseEntity<List<JobList>> getJobsPostedThisWeek() {
        List<JobList> jobsThisWeek = jobListingService.getJobsPostedThisWeek();

        if (!jobsThisWeek.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(jobsThisWeek);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @GetMapping("/jobSeeker/jobs/this-month")
    public ResponseEntity<List<JobList>> getJobsPostedThisMonth() {
        List<JobList> jobsThisMonth = jobListingService.getJobsPostedThisMonth();

        if (!jobsThisMonth.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(jobsThisMonth);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    // post jobs in employer login
    @PostMapping("/employer/post-job")
    public ResponseEntity<Object> postJobs(@RequestBody JobList job) throws HttpMessageNotReadableException {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String uname = authentication.getName();
            JobList postedJob = jobListingService.postJobs(job,uname);
            return ResponseEntity.status(HttpStatus.CREATED).body(postedJob);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error posting job" + e.getMessage());
        }
    }

    @PutMapping("/employer/edit-job/{jobId}")
    public ResponseEntity<Object> editJob(@PathVariable Long jobId, @RequestBody JobList updatedJob)
            throws HttpMessageNotReadableException {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String uname = authentication.getName();
        try {
            JobList editedJob = jobListingService.editJob(jobId, updatedJob, uname);

            if (editedJob != null) {
                return ResponseEntity.ok(editedJob);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job with ID " + jobId + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error editing job: " + e.getMessage());
        }
    }

    // display jobs by  employer
    @GetMapping("/employer")
    public ResponseEntity<List<JobList>> getJobs() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String uname = authentication.getName();
        List<JobList> jobs = jobListingService.getJobs(uname);
        return ResponseEntity.status(HttpStatus.OK).body(jobs);
    }

    // display jobs by title
    @GetMapping("/jobSeeker/search/byTitle/{title}")
    public ResponseEntity<List<JobList>> getJobsByTitle(@PathVariable("title") String title) {
        List<JobList> jobs = jobListingService.getJobsByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(jobs);
    }

    // display jobs by category
    @GetMapping("/jobSeeker/search/byCategory/{category}")
    public ResponseEntity<List<JobList>> getJobsByCategory(@PathVariable("category") String categoryName) {
        List<JobList> jobs = jobListingService.getJobsByCategory(categoryName);
        return ResponseEntity.status(HttpStatus.OK).body(jobs);
    }

    @GetMapping("/jobSeeker/search/byLocation/{location}")
    public ResponseEntity<List<JobList>> getJobsByLocation(@PathVariable("location") String cityName) {
        List<JobList> jobs = jobListingService.getJobsByLocation(cityName);
        
        return ResponseEntity.status(HttpStatus.OK).body(jobs);
    }
    // delete jobs by id
    @DeleteMapping("employer/job/delete/{jobId}")
    public String deleteJob(@PathVariable Long jobId) {
        jobListingService.deleteJob(jobId);
        return "Deleted";
    }
}
