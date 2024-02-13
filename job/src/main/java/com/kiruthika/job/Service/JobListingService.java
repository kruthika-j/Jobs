package com.kiruthika.job.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiruthika.job.Entity.Employer;
import com.kiruthika.job.Entity.JobList;
import com.kiruthika.job.Repository.EmployerRepository;
import com.kiruthika.job.Repository.JobListRepository;

@Service
public class JobListingService {

    @Autowired
    private JobListRepository jobListRepository;

    @Autowired
    private EmployerRepository employerRepository;

    public List<JobList> getAllJobs() {
        return jobListRepository.findAll();
    }

    public JobList postJobs(JobList jobList)throws Exception {
        if (!jobListRepository.existsById(jobList.getJobId())) {
            return jobListRepository.save(jobList);
       }
       else {
           throw new RuntimeException(" Job " + " already posted");
       }
    }

    public List<JobList> getJobs(String uname) {
        Employer data = employerRepository.findByUname(uname);
        return jobListRepository.findByUname(data);
    }

   public List<JobList> getJobsByTitle(String title) {
        return jobListRepository.findByTitle(title);
    }

    public List<JobList> getJobsByCategory(String category){
        return jobListRepository.findByCategory(category);
    }

    public void deleteJob(Long jobId) {
        if (jobListRepository.existsById(jobId)) {
            jobListRepository.deleteById(jobId);
        } 
       else {
            throw new RuntimeException("Job with ID " + jobId + " not found");
        }
    }

    public void deleteAllJobs(){
        jobListRepository.deleteAll();
    }
      
    public JobList editJob(Long jobId, JobList updatedJob) {
        JobList existingJob = jobListRepository.findById(jobId).get();

        if (existingJob != null) {
            existingJob.setTitle(updatedJob.getTitle());
            existingJob.setDescription(updatedJob.getDescription());
            existingJob.setRequirements(updatedJob.getRequirements());
            existingJob.setCategory(updatedJob.getCategory());
            existingJob.setDeadline(updatedJob.getDeadline());
            existingJob.setLocation(updatedJob.getLocation());
            return jobListRepository.save(existingJob);
        } else {
            return null; 
        }
    }
}
