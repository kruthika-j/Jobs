package com.kiruthika.job.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kiruthika.job.Entity.JobSeeker;
import com.kiruthika.job.Entity.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Resume findByJuname(JobSeeker juname);
    boolean existsByJuname(JobSeeker juname);

    //@Transactional
    void deleteByJuname(JobSeeker juname);
}