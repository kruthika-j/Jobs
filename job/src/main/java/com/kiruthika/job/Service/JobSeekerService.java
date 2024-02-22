package com.kiruthika.job.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.kiruthika.job.Entity.JobSeeker;
import com.kiruthika.job.Repository.JobSeekerRepository;

@Service
public class JobSeekerService{

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

      @Autowired
    private PasswordEncoder passwordEncoder;


    public JobSeeker createJobSeeker(JobSeeker jobSeeker) {
        if (!jobSeekerRepository.existsById(jobSeeker.getJuname())) {
            JobSeeker seeker = new JobSeeker();
            seeker.setContact(jobSeeker.getContact());
            seeker.setDOB(jobSeeker.getDOB());
            seeker.setEmail(jobSeeker.getEmail());
            seeker.setJuname(jobSeeker.getJuname());
            seeker.setLocation(jobSeeker.getLocation());
            seeker.setName(jobSeeker.getName());
            seeker.setQualification(jobSeeker.getQualification());
            seeker.setPassword(passwordEncoder.encode(jobSeeker.getPassword()));

            return jobSeekerRepository.save(seeker);
        } else {
            throw new RuntimeException("User " + jobSeeker.getJuname() + " already exist");
        }
    }

    public JobSeeker getJobSeeker(String juname) {
        return jobSeekerRepository.findByjuname(juname);
    }

    public void deleteJobSeeker(String juname) {
        if (jobSeekerRepository.existsById(juname)) {
            jobSeekerRepository.deleteById(juname);
        } else {
            throw new RuntimeException("User " + juname + " not found");
        }
    }

   
}
