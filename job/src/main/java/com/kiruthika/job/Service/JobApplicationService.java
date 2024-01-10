package com.kiruthika.job.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiruthika.job.Entity.JobApplication;
import com.kiruthika.job.Entity.Resume;
import com.kiruthika.job.Entity.UserData;
import com.kiruthika.job.Repository.JobApplicationRepository;
import com.kiruthika.job.Repository.UserDataRepository;

@Service
public class JobApplicationService {
    @Autowired 
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private UserDataRepository userDataRepo;
    
      public JobApplication getApplication(Long applicationId) {
            return jobApplicationRepository.findById(applicationId).orElse(null);
        }
        public List<JobApplication> getApplicationById(Long jobSeekerId) {
            UserData data = userDataRepo.findById(jobSeekerId).get();
            return jobApplicationRepository.findByjobSeekerId(data);
        }
        
    }

