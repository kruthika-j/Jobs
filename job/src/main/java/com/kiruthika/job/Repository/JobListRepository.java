package com.kiruthika.job.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiruthika.job.Entity.JobList;
import com.kiruthika.job.Entity.UserData;

@Repository
public interface JobListRepository extends JpaRepository<JobList, Long> {

    List<JobList> findByEmployerId(UserData employerId);
}