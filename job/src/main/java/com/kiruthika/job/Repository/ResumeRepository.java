package com.kiruthika.job.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kiruthika.job.Entity.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    // List<Resume> findByjobSeekerId(UserData jobSeekerId);
    // @Transactional
    // void deleteByjobSeekerId(UserData jobSeekerId);
}