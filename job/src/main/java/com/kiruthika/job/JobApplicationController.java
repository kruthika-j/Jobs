package com.kiruthika.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UserDataDTO;
import com.kiruthika.job.Service.RegistrationService;

@RestController
public class JobApplicationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/RegistrationForm")
    public ResponseEntity<String> showJobForm(@RequestBody UserDataDTO userDataDTO) {
     return new ResponseEntity<>("User created succesfully",HttpStatus.OK);
    }

    @PostMapping("/submitJobApplication")
    public ResponseEntity<String> submitJobApplication(UserDataDTO userDataDTO) {
     registrationService.registerUser(userDataDTO);

     return new ResponseEntity<>("User created succesfully",HttpStatus.OK);
    }
}