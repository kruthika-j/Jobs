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
            JobSeeker data = jobSeekerRepository.findByjuname(juname);
            if(ApplicationRepository.existsByJobSeeker(data)){
                return ApplicationRepository.findByJobSeeker(data);
            }
            else{
                throw new RuntimeException("Application not found"); 
            }
        }

        public List<Application> getApplicationByJobId(Long jobId){
            JobList data = jobListRepository.findByJobId(jobId);
            if(ApplicationRepository.existsByJobList(data)){
                return ApplicationRepository.findByJobList(data);
            }
            else{
                throw new RuntimeException("Application not found"); 
            }
        }

        public void applyForJob(String juname, JobList jobList) throws Exception {
            JobSeeker jobSeeker = jobSeekerRepository.findByjuname(juname);
            if (ApplicationRepository.existsByJobSeekerAndJobList(jobSeeker, jobList)) {
                throw new RuntimeException("JobSeeker has already applied for this job");
            }
            Date currentDate = new Date();
            if (currentDate.after(jobList.getDeadline())) {
                throw new Exception("Application deadline has passed for this job.");
            }
            Application application =  new Application();
            Resume resume = resumeRepository.findByJuname(jobSeeker);
            application.setFile(resume.getFile());
            application.setJobSeeker(jobSeeker);
            application.setJobList(jobList);
            application.setAppliedAt(LocalDateTime.now());

            ApplicationRepository.save(application);
        }
    }