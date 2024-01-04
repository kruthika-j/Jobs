package com.kiruthika.job.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiruthika.job.Entity.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
   
}