package com.kiruthika.job.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiruthika.job.Entity.Application;
import com.kiruthika.job.Entity.JobList;
import com.kiruthika.job.Entity.JobSeeker;
import com.kiruthika.job.Entity.Resume;
import com.kiruthika.job.Repository.ApplicationRepository;
import com.kiruthika.job.Repository.JobListRepository;
import com.kiruthika.job.Repository.JobSeekerRepository;
import com.kiruthika.job.Repository.ResumeRepository;

@Service
public class ApplicationService {
    @Autowired 
    private ApplicationRepository ApplicationRepository;
    @Autowired
    private JobSeekerRepository jobSeekerRepository;
    @Autowired
    private JobListRepository jobListRepository;

    @Autowired
    private ResumeRepository resumeRepository;
    
      public Application getApplication(Long applicationId) {
            return ApplicationRepository.findById(applicationId).orElse(null);
        }
        public List<Application> getApplicationByJuname(String juname) {
            JobSeeker jobSeeker = jobSeekerRepository.findByjuname(juname);
            if(ApplicationRepository.existsByJobSeeker(jobSeeker)){
                return ApplicationRepository.findByJobSeeker(jobSeeker);
            }
            else{
                throw new RuntimeException("Application not found"); 
            }
        }

        public List<Application> getApplicationByJobId(Long jobId){
            JobList job = jobListRepository.findByJobId(jobId);
            if(ApplicationRepository.existsByJobList(job)){
                return ApplicationRepository.findByJobList(job);
            }
            else{
                throw new RuntimeException("Application not found"); 
            }
        }

        public void applyForJob(String juname, Long jobId) throws Exception {
            JobSeeker jobSeeker = jobSeekerRepository.findByjuname(juname);
            JobList job = jobListRepository.findByJobId(jobId);
            if (ApplicationRepository.existsByJobSeekerAndJobList(jobSeeker, job)) {
                throw new RuntimeException("JobSeeker has already applied for this job");
            }
            Date currentDate = new Date();
            if (currentDate.after(job.getDeadline())) {
                throw new Exception("Application deadline has passed for this job.");
            }
            Application application =  new Application();
            Resume resume = resumeRepository.findByJuname(jobSeeker);
            application.setFile(resume.getFile());
            application.setJobSeeker(jobSeeker);
            application.setJobList(job);
            application.setAppliedAt(LocalDateTime.now());

            ApplicationRepository.save(application);
        }
    }