package com.kiruthika.job.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiruthika.job.Entity.JobList;
import com.kiruthika.job.Entity.UserData;
import com.kiruthika.job.Repository.JobListRepository;
import com.kiruthika.job.Repository.UserDataRepository;

@Service
public class JobListingService {

    @Autowired
    private JobListRepository jobListRepository;

    @Autowired
    private UserDataRepository userDataRepo;

    public List<JobList> getAllJobs() {
        return jobListRepository.findAll();
    }

    public JobList postJobs(JobList jobList) {
        return jobListRepository.save(jobList);
    }

    public List<JobList> getJobs(Long employerId) {
        UserData data = userDataRepo.findById(employerId).get();
        return jobListRepository.findByEmployerId(data);
    }

   public List<JobList> getJobsByTitle(String title) {
        return jobListRepository.findByTitle(title);
    }

    public void deleteJob(Long jobId) {
        jobListRepository.deleteById(jobId);
    }

    public void deleteAllJobs(){
        jobListRepository.deleteAll();
    }
        
}
