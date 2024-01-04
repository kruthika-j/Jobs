package com.kiruthika.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dto.UserDataDTO;
import com.kiruthika.job.Service.RegistrationService;

@Controller
public class JobApplicationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/RegistrationForm")
    public String showJobForm() {
        return "RegistrationForm";
    }

    @PostMapping("/submitJobApplication")
    public String submitJobApplication( UserDataDTO userDataDTO) {
     registrationService.registerUser(userDataDTO);

    //  return new ResponseEntity<>("User created succesfully",HttpStatus.OK);
    return "successpage";
    }
}
