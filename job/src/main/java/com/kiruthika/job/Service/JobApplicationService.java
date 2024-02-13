package com.kiruthika.job.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiruthika.job.Entity.JobApplicationEntity;
import com.kiruthika.job.Entity.JobList;
import com.kiruthika.job.Entity.JobSeeker;
import com.kiruthika.job.Entity.Resume;
import com.kiruthika.job.Repository.JobApplicationRepository;
import com.kiruthika.job.Repository.JobListRepository;
import com.kiruthika.job.Repository.JobSeekerRepository;
import com.kiruthika.job.Repository.ResumeRepository;

@Service
public class JobApplicationService {
    @Autowired 
    private JobApplicationRepository jobApplicationRepository;
    @Autowired
    private JobSeekerRepository jobSeekerRepository;
    @Autowired
    private JobListRepository jobListRepository;

    @Autowired
    private ResumeRepository resumeRepository;
    
      public JobApplicationEntity getApplication(Long applicationId) {
            return jobApplicationRepository.findById(applicationId).orElse(null);
        }
        public List<JobApplicationEntity> getApplicationByJuname(String juname) {
            JobSeeker data = jobSeekerRepository.findByjuname(juname);
            if(jobApplicationRepository.existsByJobSeeker(data)){
                return jobApplicationRepository.findByJobSeeker(data);
            }
            else{
                throw new RuntimeException("Application not found"); 
            }
        }

        public List<JobApplicationEntity> getApplicationByJobId(Long jobId){
            JobList data = jobListRepository.findByJobId(jobId);
            if(jobApplicationRepository.existsByJobList(data)){
                return jobApplicationRepository.findByJobList(data);
            }
            else{
                throw new RuntimeException("Application not found"); 
            }
        }

        public void applyForJob(JobSeeker jobSeeker, JobList jobList) throws Exception {
            if (jobApplicationRepository.existsByJobSeekerAndJobList(jobSeeker, jobList)) {
                throw new RuntimeException("JobSeeker has already applied for this job");
            }
            JobApplicationEntity jobApplicationEntity =  new JobApplicationEntity();
            Resume resume = resumeRepository.findByJuname(jobSeeker);
            jobApplicationEntity.setFile(resume.getFile());
            jobApplicationEntity.setJobSeeker(jobSeeker);
            jobApplicationEntity.setJobList(jobList);
            jobApplicationEntity.setAppliedAt(LocalDateTime.now());

            jobApplicationRepository.save(jobApplicationEntity);
        }
    }