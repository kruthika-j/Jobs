package com.kiruthika.job.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiruthika.job.Entity.JobSeeker;
import com.kiruthika.job.Repository.JobSeekerRepository;

@Service
public class JobSeekerService {
    
    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    public JobSeeker createJobSeeker(JobSeeker jobSeeker){
        return jobSeekerRepository.save(jobSeeker);
    }

    public JobSeeker getJobSeeker(String juname){
        return jobSeekerRepository.findByjuname(juname);
    }

    public void deleteJobSeeker(String juname){
        if (jobSeekerRepository.existsById(juname)) {
            jobSeekerRepository.deleteById(juname);
        } else {
            throw new RuntimeException("User with ID " + juname + " not found");
        }
    }
}
