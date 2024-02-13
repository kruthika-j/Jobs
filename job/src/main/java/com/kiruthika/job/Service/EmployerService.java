package com.kiruthika.job.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiruthika.job.Entity.Employer;
import com.kiruthika.job.Repository.EmployerRepository;

@Service
public class EmployerService {
    @Autowired
    private EmployerRepository employerRepository;

    public void createEmployer(Employer employer){
        if (!employerRepository.existsById(employer.getUname())) {
             employerRepository.save(employer);
        }
        else {
            throw new RuntimeException("User " + employer.getUname() + " already exist");
        }
    }

    public Employer getEmployer(String uname){
        if (employerRepository.existsById(uname)) {
            return employerRepository.findByUname(uname);
        } 
        else {
            throw new RuntimeException("User " + uname + " not found");
        }
    }

    public void deleteUser(String uname){
        if (employerRepository.existsById(uname)) {
            employerRepository.deleteById(uname);
         } 
       else {
            throw new RuntimeException("User " + uname + " not found");
        }
    }
}
