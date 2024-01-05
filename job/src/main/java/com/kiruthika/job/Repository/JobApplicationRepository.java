package com.kiruthika.job.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kiruthika.job.Entity.JobApplication;
import com.kiruthika.job.Entity.UserData;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

   
}