package com.kiruthika.job;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
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
   public ResponseEntity<Object> createEmployer(@Valid @RequestBody Employer employer)throws HttpMessageNotReadableException{
         employerService.createEmployer(employer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registered successfully");
   }
//jobseeker signup
   @PostMapping("/register/jobseeker")
   public ResponseEntity<Object> createJobSeeker(@Valid @RequestBody JobSeeker jobSeeker) throws HttpMessageNotReadableException{
       JobSeeker newJobSeeker = jobSeekerService.createJobSeeker(jobSeeker);
       return ResponseEntity.status(HttpStatus.CREATED).body(newJobSeeker);
   }
   
//employer details
   @GetMapping("/employer/profile/{uname}")
   public ResponseEntity<Object> getEmployer(@PathVariable String uname)throws NoResourceFoundException {
       Employer employer = employerService.getEmployer(uname);
       return ResponseEntity.status(HttpStatus.OK).body(employer);
   }
//jobseeker details
   @GetMapping("/jobSeeker/profile/{juname}")
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
    @DeleteMapping("/jobSeeker/delete/{juname}")
    public String deleteJobSeeker(@PathVariable String juname)throws NoResourceFoundException {
            jobSeekerService.deleteJobSeeker(juname);
            return "User Deleted";
    }
//jobs display in jobseeker login
    @GetMapping("/jobSeeker/jobs")
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
            try{
                JobList postedJob = jobListingService.postJobs(joblist);
                return ResponseEntity.status(HttpStatus.CREATED).body(postedJob);    
            }
            catch(Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( "Error posting job" + e.getMessage());
            }
        }

        @PutMapping("/employer/edit-job/{jobId}")
        public ResponseEntity<Object> editJob(@PathVariable Long jobId, @RequestBody JobList updatedJob) throws HttpMessageNotReadableException {
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

//display jobs by uname of employer
    @GetMapping("/employer/{uname}")
    public ResponseEntity<List<JobList>> getJobs(@PathVariable String uname)throws Exception {
        List<JobList> jobList = jobListingService.getJobs(uname);
        return ResponseEntity.status(HttpStatus.OK).body(jobList);
    }
//display jobs by title
    @GetMapping("/jobSeeker/search/byTitle/{title}")
    public ResponseEntity<List<JobList>> getJobsByTitle(@PathVariable("title") String title) {
        List<JobList> jobList = jobListingService.getJobsByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(jobList);
    }
//display jobs by category
    @GetMapping("/jobSeeker/search/byCategory/{category}")
    public ResponseEntity<List<JobList>> getJobsByCategory(@PathVariable("category") String category){
        List<JobList> joblist = jobListingService.getJobsByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(joblist);
    }
//delete jobs by id
    @DeleteMapping("employer/job/delete/{jobId}")
    public String deleteJob(@PathVariable Long jobId){
        jobListingService.deleteJob(jobId);
        return "Deleted";
    }
//delete all jobs
    @DeleteMapping("/jobs/deleteAll")
    public String deleteAllJobs() {
        jobListingService.deleteAllJobs();
        return "All jobs deleted";
    }
//get all resumes
    @GetMapping("jobSeeker/profile/resumes")
    public ResponseEntity<List<Resume>> getAllResumes() {
        List<Resume> resumes = resumeManagementService.getAllResumes();
        return ResponseEntity.status(HttpStatus.OK).body(resumes);
    }

    @PostMapping("/jobSeeker/profile/post-resumes/{uname}")
    public ResponseEntity<Object> postResumes(
           @PathVariable String uname,
           @RequestParam("file") MultipartFile file
    ){
        try {
            resumeManagementService.postResumes(file, uname);
            return ResponseEntity.status(HttpStatus.CREATED).body("Resume Uploaded Successfully!");
        }
        catch (MultipartException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to parse multipart request!");
        }
        catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file!");
        }
    }

    @DeleteMapping("jobSeeker/profile/resume/delete/{juname}")
    public String deleteResume(@PathVariable String juname){
        resumeManagementService.deleteResume(juname);
        return "Resume Deleted";
    }
  
    @GetMapping("/employer/job/view-application/{applicationId}")
    public ResponseEntity<Object> getApplication(@PathVariable Long applicationId) throws Exception {
        try {JobApplicationEntity jobApplication = jobApplicationService.getApplication(applicationId);
    
            if (jobApplication != null) {
                return ResponseEntity.status(HttpStatus.OK).body(jobApplication);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("JobApplication with ID " + applicationId + " not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving JobApplication!");
        }
    }
    

    @GetMapping("/jobSeeker/jobs-applied/{juname}")
    public ResponseEntity<List<JobApplicationEntity>> getApplicationByJobSeekerJuname(@PathVariable String 
    juname)throws Exception{
        List<JobApplicationEntity> applications = jobApplicationService.getApplicationByJuname(juname);
        return ResponseEntity.status(HttpStatus.OK).body(applications);
    }

    @GetMapping("/employer/job/view-application/by-job-id/{jobId}")
    public ResponseEntity<List<JobApplicationEntity>> getApplicationByJobId(@PathVariable Long jobId){
        List<JobApplicationEntity> applications = jobApplicationService.getApplicationByJobId(jobId);
        return ResponseEntity.status(HttpStatus.OK).body(applications);
    }

    @PostMapping("/jobSeeker/job/apply")
    public ResponseEntity<Object> applyForJob(@RequestBody JobApplicationEntity request) {
        try {
            jobApplicationService.applyForJob(request.getJobSeeker(), request.getJobList());
            return ResponseEntity.status(HttpStatus.CREATED).body("Job application submitted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error applying for the job: " + e.getMessage());
        }
    }
}
