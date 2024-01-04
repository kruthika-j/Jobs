package com.kiruthika.job.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiruthika.job.Entity.JobList;

public interface JobListRepository extends JpaRepository<JobList, Long> {

   
}