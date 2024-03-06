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
        List<JobList> jobList = jobListingService.getAllJobs();

        if (!jobList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(jobList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
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
    public ResponseEntity<Object> postJobs(@RequestBody JobList joblist) throws HttpMessageNotReadableException {
        try {
            JobList postedJob = jobListingService.postJobs(joblist);
            return ResponseEntity.status(HttpStatus.CREATED).body(postedJob);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error posting job" + e.getMessage());
        }
    }

    @PutMapping("/employer/edit-job/{jobId}")
    public ResponseEntity<Object> editJob(@PathVariable Long jobId, @RequestBody JobList updatedJob)
            throws HttpMessageNotReadableException {
        try {
            JobList editedJob = jobListingService.editJob(jobId, updatedJob);

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
        List<JobList> jobList = jobListingService.getJobs(uname);
        return ResponseEntity.status(HttpStatus.OK).body(jobList);
    }

    // display jobs by title
    @GetMapping("/jobSeeker/search/byTitle/{title}")
    public ResponseEntity<List<JobList>> getJobsByTitle(@PathVariable("title") String title) {
        List<JobList> jobList = jobListingService.getJobsByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(jobList);
    }

    // display jobs by category
    @GetMapping("/jobSeeker/search/byCategory/{category}")
    public ResponseEntity<List<JobList>> getJobsByCategory(@PathVariable("category") String category) {
        List<JobList> joblist = jobListingService.getJobsByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(joblist);
    }

    // delete jobs by id
    @DeleteMapping("employer/job/delete/{jobId}")
    public String deleteJob(@PathVariable Long jobId) {
        jobListingService.deleteJob(jobId);
        return "Deleted";
    }
}
