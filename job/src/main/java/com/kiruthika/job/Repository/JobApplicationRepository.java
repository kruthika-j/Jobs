package com.kiruthika.job.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiruthika.job.Entity.JobApplication;
import com.kiruthika.job.Entity.Resume;
import com.kiruthika.job.Entity.UserData;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
List<Resume> findByjobSeekerId(UserData jobSeekerId);
    
}