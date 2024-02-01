package com.kiruthika.job.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiruthika.job.Entity.JobApplicationEntity;
import com.kiruthika.job.Entity.JobList;
import com.kiruthika.job.Entity.JobSeeker;
import com.kiruthika.job.Repository.JobApplicationRepository;
import com.kiruthika.job.Repository.JobListRepository;
import com.kiruthika.job.Repository.JobSeekerRepository;

@Service
public class JobApplicationService {
    @Autowired 
    private JobApplicationRepository jobApplicationRepository;
    @Autowired
    private JobSeekerRepository jobSeekerRepository;
    @Autowired
    private JobListRepository jobListRepository;
    
      public JobApplicationEntity getApplication(Long applicationId) {
            return jobApplicationRepository.findById(applicationId).orElse(null);
        }
        public List<JobApplicationEntity> getApplicationByJuname(String juname) {
            JobSeeker data = jobSeekerRepository.findByjuname(juname);
            return jobApplicationRepository.findByJobSeeker(data);
        }

        public List<JobApplicationEntity> getApplicationByJobId(Long jobId){
            JobList data = jobListRepository.findByJobId(jobId);
            return jobApplicationRepository.findByJobList(data);
        }

        public void applyForJob(JobSeeker jobSeeker, JobList jobList) throws Exception {
            if (jobApplicationRepository.existsByJobSeekerAndJobList(jobSeeker, jobList)) {
                throw new RuntimeException("JobSeeker has already applied for this job");
            }
            JobApplicationEntity jobApplicationEntity =  new JobApplicationEntity();
            jobApplicationEntity.setJobSeeker(jobSeeker);
            jobApplicationEntity.setJobList(jobList);
            jobApplicationRepository.save(jobApplicationEntity);
        }   
    }
    

