package com.kiruthika.job.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiruthika.job.Repository.JobListRepository;

@Service
public class JobListingService {
    @Autowired
    private JobListRepository jobListRepository;
    
}
