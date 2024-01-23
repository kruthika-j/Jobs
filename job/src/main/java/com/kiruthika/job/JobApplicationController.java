package com.kiruthika.job;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.kiruthika.job.Entity.JobList;
import com.kiruthika.job.Entity.JobSeeker;
import com.kiruthika.job.Entity.Resume;
import com.kiruthika.job.Entity.Employer;
import com.kiruthika.job.Entity.JobApplicationEntity;
import com.kiruthika.job.Service.EmployerService;
import com.kiruthika.job.Service.JobApplicationService;
import com.kiruthika.job.Service.JobListingService;
import com.kiruthika.job.Service.JobSeekerService;
import com.kiruthika.job.Service.ResumeService;
// import com.kiruthika.job.Exception.EmptyListException;

import jakarta.validation.Valid;

@RestController
public class JobApplicationController {
    private JobListingService jobListingService;
    private ResumeService resumeManagementService;
    private JobApplicationService jobApplicationService;
    private EmployerService employerService;
    private JobSeekerService jobSeekerService;

    @Autowired
    public JobApplicationController( JobListingService jobListingService,
            ResumeService resumeManagementService, JobApplicationService jobApplicationService, EmployerService employerService,JobSeekerService jobSeekerService) {
        this.jobListingService = jobListingService;
        this.resumeManagementService = resumeManagementService;
        this.jobApplicationService = jobApplicationService;
        this.employerService = employerService;
        this.jobSeekerService = jobSeekerService;

    }
//Employer signup
   @PostMapping("/register/employer")
   public ResponseEntity<Object> createEmployer(@Valid @RequestBody Employer employer)throws RuntimeException,HttpMessageNotReadableException{
        Employer newEmployer = employerService.createEmployer(employer);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployer);
   }
//jobseeker signup
   @PostMapping("/register/jobseeker")
   public ResponseEntity<Object> createJobSeeker(@RequestBody JobSeeker jobSeeker) throws RuntimeException, HttpMessageNotReadableException{
       JobSeeker newJobSeeker = jobSeekerService.createJobSeeker(jobSeeker);
       return ResponseEntity.status(HttpStatus.CREATED).body(newJobSeeker);
   }
   
//employer details
   @GetMapping("/employer/{uname}")
   public ResponseEntity<Object> getEmployer(@PathVariable String uname)throws NoResourceFoundException {
       Employer employer = employerService.getEmployer(uname);
       return ResponseEntity.status(HttpStatus.OK).body(employer);
   }
//jobseeker details
   @GetMapping("/jobseeker/{juname}")
   public ResponseEntity<Object> getjobSeeker(@PathVariable String juname) throws NoResourceFoundException {
        JobSeeker jobSeeker = jobSeekerService.getJobSeeker(juname);
        return ResponseEntity.status(HttpStatus.OK).body(jobSeeker);
   }
    
//delete employer by username
    @DeleteMapping("/employer/delete/{uname}")
    public String deleteUser(@PathVariable String uname)throws NoResourceFoundException {
        try {
            employerService.deleteUser(uname);
            return "User removed";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
//delete jobseeker by juname
    @DeleteMapping("/jobseeker/delete/{juname}")
    public String deleteJobSeeker(@PathVariable String juname)throws NoResourceFoundException {
            jobSeekerService.deleteJobSeeker(juname);
            return "User Deleted";
    }
//jobs display in jobseeker login
    @GetMapping("/jobseeker/jobs")
    public ResponseEntity<List<JobList>> getAllJobs() {
        List<JobList> jobList = jobListingService.getAllJobs();

        if (!jobList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(jobList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

//post jobs in employer login
    @PostMapping("/employer/post-job")
        public ResponseEntity<Object> postJobs(@RequestBody JobList joblist)throws HttpMessageNotReadableException{
            JobList postedJob = jobListingService.postJobs(joblist);
            return ResponseEntity.status(HttpStatus.CREATED).body(postedJob);
        }
//display jobs by uname of employer
    @GetMapping("/jobs/employer/{uname}")
    public ResponseEntity<List<JobList>> getJobs(@PathVariable String uname)throws Exception {
        List<JobList> jobList = jobListingService.getJobs(uname);
        return ResponseEntity.status(HttpStatus.OK).body(jobList);
    }
//display jobs by title
    @GetMapping("/jobs/{title}")
    public ResponseEntity<List<JobList>> getJobsByTitle(@PathVariable("title") String title) {
        List<JobList> jobList = jobListingService.getJobsByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(jobList);
    }
//delete jobs by id
    @DeleteMapping("/jobs/delete/{jobId}")
    public String deleteJob(@PathVariable Long jobId){
        jobListingService.deleteJob(jobId);
        return "Success";
    }
//delete all jobs
    @DeleteMapping("/jobs/deleteAll")
    public String deleteAllJobs() {
        jobListingService.deleteAllJobs();
        return "All jobs deleted";
    }
//get all resumes
    @GetMapping("/resumes")
    public ResponseEntity<List<Resume>> getAllResumes() {
        List<Resume> resumes = resumeManagementService.getAllResumes();
        return ResponseEntity.status(HttpStatus.OK).body(resumes);
    }

    // @GetMapping("/resumes/{jobSeekerId}")
    // public ResponseEntity<List<Resume>> getResumeByUserId(@PathVariable Long jobSeekerId) {
    //     List<Resume> resumes = resumeManagementService.getResumeById(jobSeekerId);
    //     return ResponseEntity.status(HttpStatus.OK).body(resumes);
    // }

     @PostMapping("/post-resumes")
    public ResponseEntity<Object> postResumes(
            @ModelAttribute Resume resume,
            @RequestParam("filePath") MultipartFile file
    )throws HttpMessageNotReadableException {
        try {
            Resume postedResume = resumeManagementService.postResumes(file, resume);
            return ResponseEntity.status(HttpStatus.CREATED).body(postedResume);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
    }

    // @DeleteMapping("/resumes/delete/{jobSeekerId}")
    // public String deleteResumeById(@PathVariable Long jobSeekerId) {
    //     resumeManagementService.deleteAllResumesByJobSeekerId(jobSeekerId);
    //     return "resumes deleted";
    // }

    @GetMapping("/application/{applicationId}")
    public ResponseEntity<Object> getApplication(@PathVariable Long applicationId)throws Exception{
        JobApplicationEntity jobApplication = jobApplicationService.getApplication(applicationId);
        return ResponseEntity.status(HttpStatus.OK).body(jobApplication);
    }

    @GetMapping("/application/jobSeeker/{jobSeekerId}")
    public ResponseEntity<List<JobApplicationEntity>> getApplicationByJobSeekerId(@PathVariable Long jobSeekerId)throws Exception{
        List<JobApplicationEntity> applications = jobApplicationService.getApplicationById(jobSeekerId);
        return ResponseEntity.status(HttpStatus.OK).body(applications);
    }
}
