package com.kiruthika.job.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiruthika.job.Entity.Application;
import com.kiruthika.job.Entity.JobList;
import com.kiruthika.job.Entity.JobSeeker;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByJobSeeker(JobSeeker jobSeeker);
    boolean existsByJobSeekerAndJobList(JobSeeker jobSeeker,JobList jobList);
    boolean existsByJobSeeker(JobSeeker jobSeeker);
    boolean existsByJobList(JobList jobList);
    List<Application> findByJobList(JobList jobList);
}