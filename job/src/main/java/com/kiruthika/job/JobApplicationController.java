package com.kiruthika.job;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kiruthika.job.Entity.JobList;
import com.kiruthika.job.Entity.JobSeeker;
import com.kiruthika.job.Entity.Resume;
import com.kiruthika.job.Entity.UserData;
import com.kiruthika.job.Entity.Employer;
import com.kiruthika.job.Entity.JobApplicationEntity;
import com.kiruthika.job.Service.EmployerService;
import com.kiruthika.job.Service.JobApplicationService;
import com.kiruthika.job.Service.JobListingService;
import com.kiruthika.job.Service.RegistrationService;
import com.kiruthika.job.Service.ResumeService;
// import com.kiruthika.job.Exception.EmptyListException;

@RestController
public class JobApplicationController {
    private RegistrationService registrationService;
    private JobListingService jobListingService;
    private ResumeService resumeManagementService;
    private JobApplicationService jobApplicationService;
    private EmployerService employerService;

    @Autowired
    public JobApplicationController(RegistrationService registrationService, JobListingService jobListingService,
            ResumeService resumeManagementService, JobApplicationService jobApplicationService, EmployerService employerService) {
        this.registrationService = registrationService;
        this.jobListingService = jobListingService;
        this.resumeManagementService = resumeManagementService;
        this.jobApplicationService = jobApplicationService;
        this.employerService = employerService;
    }
//Employer signup
   @PostMapping("/register/employer")
   public ResponseEntity<Object> createEmployer(@RequestBody Employer employer){
        Employer newEmployer = employerService.createEmployer(employer);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployer);
   }
//jobseeker signup
   @PostMapping("/register/jobseeker")
   public ResponseEntity<Object> createJobSeeker(@RequestBody JobSeeker jobSeeker) {
       JobSeeker newJobSeeker = jobSeekerService.createJobSeeker(jobSeeker);
       return ResponseEntity.status(HttpStatus.CREATED).body(newJobSeeker);
   }
   
//employer details
   @GetMapping("/employer/{uname}")
   public ResponseEntity<Object> getEmployer(@PathVariable String uname) {
       Employer employer = employerService.getEmployer(uname);
       return ResponseEntity.status(HttpStatus.OK).body(employer);
   }
   

    // @GetMapping("/users/{userId}")
    // public ResponseEntity<Object> showJobForm(@PathVariable Long userId) throws Exception {
    //     UserData userData = registrationService.getUser(userId);
    //     return ResponseEntity.status(HttpStatus.OK).body(userData);
    // }

    // @GetMapping("/users")
    // public ResponseEntity<List<UserData>> getAllUsers()throws Exception  {
    //     List<UserData> allUsers = registrationService.getAllUsers();
    //     return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    // }

    // @PostMapping("/create-users")
    // public ResponseEntity<Object> createUser(@RequestBody UserData userData) {
    //     validateUserData(userData);
    //     UserData createdUser = registrationService.createUser(userData);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    // }
    // private void validateUserData(UserData userData) {
    //     if (userData.getName() == null || userData.getName().isEmpty()) {
    //         throw new IllegalArgumentException("Name must not be empty");
    //     }
    //     if (userData.getPassword() == null || userData.getPassword().isEmpty()) {
    //         throw new IllegalArgumentException("Password must not be empty");
    //     }
    //     if (userData.getRole() == null ) {
    //         throw new IllegalArgumentException("Invalid role");
    //     }
    // }

    
//delete employer by username
    @DeleteMapping("/employer/delete/uname")
    public String deleteUser(@PathVariable String uname) {
        try {
            employerService.deleteUser(uname);
            return "User removed";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
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
        public ResponseEntity<Object> postJobs(@RequestBody JobList joblist){
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
    ) {
        try {
            resume.setFilePath(file.getBytes());
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
