package com.kiruthika.job.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import com.kiruthika.job.Entity.Resume;
import com.kiruthika.job.Entity.UserData;
import com.kiruthika.job.Repository.ResumeRepository;
import com.kiruthika.job.Repository.UserDataRepository;


@Service
public class ResumeService {
    @Autowired
    private ResumeRepository resumeManagementRepository;

    @Autowired
    private UserDataRepository userDataRepo;

    public List<Resume> getAllResumes() {
        return resumeManagementRepository.findAll();
    }

    // public List<Resume> getResumeById(Long jobSeekerId) {
    //     UserData data = userDataRepo.findById(jobSeekerId).get();
    //     return resumeManagementRepository.findByjobSeekerId(data);
    // }

    public Resume postResumes(MultipartFile file,Resume resumeManagement) throws IOException {
        resumeManagement.setFilePath(file.getBytes());
        return resumeManagementRepository.save(resumeManagement);
    }

    // public void deleteAllResumesByJobSeekerId(Long jobSeekerId) {
    //     UserData userData = userDataRepo.findById(jobSeekerId).get();
    //     resumeManagementRepository.deleteByjobSeekerId(userData);
    // }
}
