package com.kiruthika.job.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiruthika.job.Entity.JobApplicationEntity;
import com.kiruthika.job.Entity.UserData;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplicationEntity, Long> {
List<JobApplicationEntity> findByjobSeekerId(UserData jobSeekerId);
    
}