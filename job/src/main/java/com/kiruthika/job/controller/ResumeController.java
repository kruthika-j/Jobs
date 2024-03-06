package com.kiruthika.job.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import com.kiruthika.job.Entity.Resume;
import com.kiruthika.job.Service.ResumeService;

@RestController
public class ResumeController {

    @Autowired
    private ResumeService resumeManagementService;

    // get all resumes
    @GetMapping("jobSeeker/profile/resumes")
    public ResponseEntity<Resume> getResume() {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String juname = authentication.getName();
        Resume resumes = resumeManagementService.getResumes(juname);
        return ResponseEntity.status(HttpStatus.OK).body(resumes);
    }

    @PostMapping("/jobSeeker/profile/post-resumes")
    public ResponseEntity<Object> postResumes(
           
            @RequestParam("file") MultipartFile file) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            String uname = authentication.getName();
            resumeManagementService.postResumes(file, uname);
            return ResponseEntity.status(HttpStatus.CREATED).body("Resume Uploaded Successfully!");
        } catch (MultipartException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to parse multipart request!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file!");
        }
    }

    @DeleteMapping("jobSeeker/profile/resume/delete")
    public String deleteResume() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String juname = authentication.getName();
        resumeManagementService.deleteResume(juname);
        return "Resume Deleted";
    }
}
