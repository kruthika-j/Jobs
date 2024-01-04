package com.kiruthika.job.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiruthika.job.Entity.UserData;

public interface JobApplicationRepository extends JpaRepository<UserData, Long> {

   
}
