package com.kiruthika.job.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.kiruthika.job.Entity.JobSeeker;
import com.kiruthika.job.Entity.Resume;
import com.kiruthika.job.Repository.JobSeekerRepository;
import com.kiruthika.job.Repository.ResumeRepository;


@Service
public class ResumeService {
    @Autowired
    private ResumeRepository resumeManagementRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;


    public List<Resume> getAllResumes() {
        return resumeManagementRepository.findAll();
    }

    public void deleteResume(String juname){
        JobSeeker jobSeeker = jobSeekerRepository.findByjuname(juname);
        resumeManagementRepository.deleteByJuname(jobSeeker);
    }

    public Resume postResumes(MultipartFile file, String uname) throws IOException {
        try {
            byte[] fileData = file.getBytes();
            JobSeeker jobSeeker = jobSeekerRepository.findByjuname(uname);
            Resume resume = new Resume();
            resume.setJuname(jobSeeker);
            resume.setFile(fileData);
            resume.setUpdationDate(new Date(System.currentTimeMillis()));
            Resume postedResume = resumeManagementRepository.save(resume);
            return postedResume;
        } catch (IOException e) {
            e.printStackTrace(); 
            throw new IOException("Error uploading file", e);
        } catch (Exception e) {
            e.printStackTrace(); 
            throw new RuntimeException("Error posting resumes", e);
        }
    }

   

}
