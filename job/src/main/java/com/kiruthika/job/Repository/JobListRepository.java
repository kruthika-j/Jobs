package com.kiruthika.job.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiruthika.job.Entity.Category;
import com.kiruthika.job.Entity.Employer;
import com.kiruthika.job.Entity.JobList;
import com.kiruthika.job.Entity.Location;

@Repository
public interface JobListRepository extends JpaRepository<JobList, Long> {
    JobList findByJobId(Long jobId);
    List<JobList> findByUname(Employer uname);
    List<JobList> findByTitle(String Title);
    List<JobList> findByCategory(Category category);
    List<JobList> findByLocation(Location location);
    void deleteById(Long jobId);
    List<JobList> findByPostedDate(LocalDateTime PostedDate);
    List<JobList> findByPostedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}