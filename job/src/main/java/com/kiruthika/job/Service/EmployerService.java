package com.kiruthika.job.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kiruthika.job.Entity.Employer;
import com.kiruthika.job.Repository.EmployerRepository;

@Service
public class EmployerService{
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void createEmployer(Employer employer){
        if (!employerRepository.existsById(employer.getUname())) {
            Employer emp = new Employer();
            emp.setCompanyId(employer.getCompanyId());
            emp.setCompanyName(employer.getCompanyName());
            emp.setContact(employer.getContact());
            emp.setLocation(employer.getLocation());
            emp.setUname(employer.getUname());
            emp.setWebsite(employer.getWebsite());
            emp.setDesignation(employer.getDesignation());
            emp.setPassword(passwordEncoder.encode(employer.getPassword()));
            employerRepository.save(emp);
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
