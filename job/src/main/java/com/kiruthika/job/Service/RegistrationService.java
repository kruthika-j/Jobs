package com.kiruthika.job.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.UserDataDTO;
import com.kiruthika.job.Entity.UserData;


    @Service
    public class RegistrationService {

        @Autowired
        private UserData jobApplicationRepository;

        public UserData registerUser(UserDataDTO userDataDTO) {
            UserData userData = new UserData(userDataDTO.getName(), userDataDTO.getPassword());
            return jobApplicationRepository.save(userData);
        }
    }

