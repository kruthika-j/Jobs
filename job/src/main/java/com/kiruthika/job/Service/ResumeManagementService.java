package com.kiruthika.job.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.kiruthika.job.Entity.JobList;
import com.kiruthika.job.Entity.ResumeManagement;
import com.kiruthika.job.Entity.UserData;
import com.kiruthika.job.Repository.ResumeManagementRepository;
import com.kiruthika.job.Repository.UserDataRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ResumeManagementService {
    @Autowired
    private ResumeManagementRepository resumeManagementRepository;

    @Autowired
    private UserDataRepository userDataRepo;

    public List<ResumeManagement> getAllResumes() {
        return resumeManagementRepository.findAll();
    }

    public List<ResumeManagement> getResumeById(Long jobSeekerId) {
        UserData data = userDataRepo.findById(jobSeekerId).get();
        return resumeManagementRepository.findByjobSeekerId(data);
    }

    public ResumeManagement postResumes(ResumeManagement resumeManagement) {
        return resumeManagementRepository.save(resumeManagement);
    }

    public void deleteAllResumesByJobSeekerId(Long jobSeekerId) {
        UserData userData = userDataRepo.findById(jobSeekerId).get();
        System.out.println("UserData:::" + userData.getName());
        resumeManagementRepository.deleteByjobSeekerId(userData);
    }
}
