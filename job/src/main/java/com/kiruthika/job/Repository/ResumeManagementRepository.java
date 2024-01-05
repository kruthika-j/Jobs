package com.kiruthika.job.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiruthika.job.Entity.ResumeManagement;
@Repository
public interface ResumeManagementRepository extends JpaRepository<ResumeManagement, Long> {

   
}