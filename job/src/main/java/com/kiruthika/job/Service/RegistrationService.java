package com.kiruthika.job.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.UserDataDTO;
import com.kiruthika.job.Entity.JobApplication;
import com.kiruthika.job.Entity.UserData;
import com.kiruthika.job.Repository.JobApplicationRepository;
import com.kiruthika.job.Repository.UserDataRepository;


    @Service
    public class RegistrationService {

        @Autowired
        private UserDataRepository userDataRepository;

        public UserData getUser(Long userId) {
            return userDataRepository.findById(userId).orElse(null);
        }

        public List<UserData> getAllUsers() {
            return userDataRepository.findAll();
        }

        public UserData createUser(UserData userData) {
                return userDataRepository.save(userData);
        }
        
        public void deleteUser(Long userId){
            if (userDataRepository.existsById(userId)) {
                userDataRepository.deleteById(userId);
            } else {
                throw new RuntimeException("User with ID " + userId + " not found");
            }
        }
    }

