package com.kiruthika.job.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.UserDataDTO;
import com.kiruthika.job.Entity.UserData;
import com.kiruthika.job.Repository.JobApplicationRepository;
import com.kiruthika.job.Repository.UserDataRepository;


    @Service
    public class RegistrationService {

        @Autowired
        private JobApplicationRepository jobApplicationRepository;

        public UserData registerUser(UserDataDTO userDataDTO) {
            UserData userData = new UserData(userDataDTO.getName(), userDataDTO.getPassword());
            return UserDataRepository.save(userData);
        }
    }

