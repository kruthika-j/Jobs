package com.kiruthika.job.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiruthika.job.Entity.Employer;
import com.kiruthika.job.Entity.JobList;

@Repository
public interface JobListRepository extends JpaRepository<JobList, Long> {
    JobList findByJobId(Long jobId);
    List<JobList> findByUname(Employer uname);
    List<JobList> findByTitle(String Title);
    List<JobList> findByCategory(String category);
    void deleteById(Long jobId);
    void deleteAll();
}