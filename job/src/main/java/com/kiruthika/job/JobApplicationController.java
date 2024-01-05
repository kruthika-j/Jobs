package com.kiruthika.job;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UserDataDTO;
import com.kiruthika.job.Entity.JobApplication;
import com.kiruthika.job.Entity.UserData;
import com.kiruthika.job.Service.RegistrationService;

@RestController
public class JobApplicationController {
    private RegistrationService registrationService;

    @Autowired
    public JobApplicationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<Object> showJobForm(@PathVariable Long userId) {
        UserData userData = registrationService.getUser(userId);
        if(userData!=null){
            return ResponseEntity.status(HttpStatus.OK).body(userData);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserData>> getAllUsers() {
        List<UserData> allUsers = registrationService.getAllUsers();

        if (!allUsers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(allUsers);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }


    // @PostMapping("/submitJobApplication")
    // public ResponseEntity<String> submitJobApplication(UserDataDTO userDataDTO) {
    //  registrationService.registerUser(userDataDTO);

    //  return new ResponseEntity<>("User created succesfully",HttpStatus.OK);
    // }
}