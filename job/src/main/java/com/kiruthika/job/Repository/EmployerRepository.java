package com.kiruthika.job.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiruthika.job.Entity.Employer;

import jakarta.transaction.Transactional;

@Repository
public interface EmployerRepository extends JpaRepository<Employer,String> {
   
    Employer findByUname(String Uname);
}