package com.kiruthika.job.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiruthika.job.Entity.JobApplicationEntity;
import com.kiruthika.job.Entity.JobList;
import com.kiruthika.job.Entity.JobSeeker;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplicationEntity, Long> {
    List<JobApplicationEntity> findByJobSeeker(JobSeeker jobSeeker);
    boolean existsByJobSeekerAndJobList(JobSeeker jobSeeker,JobList jobList);
    List<JobApplicationEntity> findByJobList(JobList jobList);

}