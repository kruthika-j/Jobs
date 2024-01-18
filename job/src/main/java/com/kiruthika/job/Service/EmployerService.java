package com.kiruthika.job.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiruthika.job.Entity.Employer;
import com.kiruthika.job.Repository.EmployerRepository;

@Service
public class EmployerService {
    @Autowired
    private EmployerRepository employerRepository;

    public Employer createEmployer(Employer employer){
        return employerRepository.save(employer);
    }

    public Employer getEmployer(String uname){
        return employerRepository.findByUname(uname);
    }

    public void deleteUser(String uname){
        if (employerRepository.existsById(uname)) {
            employerRepository.deleteById(uname);
        } else {
            throw new RuntimeException("User with ID " + uname + " not found");
        }
    }
}
