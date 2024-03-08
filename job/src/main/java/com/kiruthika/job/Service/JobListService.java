package com.kiruthika.job.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiruthika.job.Entity.Category;
import com.kiruthika.job.Entity.Employer;
import com.kiruthika.job.Entity.JobList;
import com.kiruthika.job.Entity.JobSeeker;
import com.kiruthika.job.Entity.Location;
import com.kiruthika.job.Repository.CategoryRepository;
import com.kiruthika.job.Repository.EmployerRepository;
import com.kiruthika.job.Repository.JobListRepository;
import com.kiruthika.job.Repository.JobSeekerRepository;
import com.kiruthika.job.Repository.LocationRepository;

@Service
public class JobListService {

    @Autowired
    private JobListRepository jobListRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    public List<JobList> getAllJobs() {
        List<JobList> allJobs = jobListRepository.findAll();

        Date currentDate = new Date();
        List<JobList> activeJobs = allJobs.stream()
                .filter(job -> job.getDeadline().after(currentDate))
                .collect(Collectors.toList());

        return activeJobs;
    }

    public List<JobList> getJobsInMyLocation(String uname){
        JobSeeker jobSeeker  = jobSeekerRepository.findByjuname(uname);
        Location seekerLocation = jobSeeker.getLocation();
        //String stateName = empLocation.getStateName();
        List<JobList> allJobs = jobListRepository.findByLocation(seekerLocation);
        return allJobs;
    }

    public List<JobList> getJobsPostedToday() {
        LocalDateTime startOfDay = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);

        return jobListRepository.findByPostedDateBetween(startOfDay, endOfDay);
    }

    public List<JobList> getJobsPostedThisWeek() {
        LocalDateTime startOfWeek = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime endOfWeek = startOfWeek.plusDays(6);
        return jobListRepository.findByPostedDateBetween(startOfWeek, endOfWeek);
    }

    public List<JobList> getJobsPostedThisMonth() {
        LocalDateTime startOfMonth = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime endOfMonth = startOfMonth.with(TemporalAdjusters.lastDayOfMonth());
        return jobListRepository.findByPostedDateBetween(startOfMonth, endOfMonth);
    }

    public JobList postJobs(JobList jobList, String uname) throws Exception {

        Employer employer = employerRepository.findByUname(uname);
        if (!jobListRepository.existsById(jobList.getJobId())) {
            JobList job = new JobList();
            job.setCategory(jobList.getCategory());
            job.setDeadline(jobList.getDeadline());
            job.setDescription(jobList.getDescription());
            job.setJobId(jobList.getJobId());
            job.setLocation(jobList.getLocation());
            job.setRequirements(jobList.getRequirements());
            job.setTitle(jobList.getTitle());
            job.setUname(employer);
            job.setPostedDate(LocalDateTime.now());
            return jobListRepository.save(job);
        } else {
            throw new RuntimeException(" Job " + " already posted");
        }
    }

    public List<JobList> getJobs(String uname) {
        Employer data = employerRepository.findByUname(uname);
        return jobListRepository.findByUname(data);
    }

    public List<JobList> getJobsByTitle(String title) {
        return jobListRepository.findByTitle(title);
    }

    public List<JobList> getJobsByCategory(String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName);
        return jobListRepository.findByCategory(category);
            }

    public List<JobList> getJobsByLocation(String stateName) {
        Location location = locationRepository.findByStateName(stateName);
        return jobListRepository.findByLocation(location);
    }

    public void deleteJob(Long jobId) {
        if (jobListRepository.existsById(jobId)) {
            jobListRepository.deleteById(jobId);
        } else {
            throw new RuntimeException("Job with ID " + jobId + " not found");
        }
    }

    public JobList editJob(Long jobId, JobList updatedJob,String uname) {
        JobList existingJob = jobListRepository.findById(jobId).get();
        Employer employer = employerRepository.findByUname(uname);

        if (existingJob != null) {
            existingJob.setTitle(updatedJob.getTitle());
            existingJob.setDescription(updatedJob.getDescription());
            existingJob.setRequirements(updatedJob.getRequirements());
            existingJob.setCategory(updatedJob.getCategory());
            existingJob.setDeadline(updatedJob.getDeadline());
            existingJob.setLocation(updatedJob.getLocation());
            existingJob.setUname(employer);
            return jobListRepository.save(existingJob);
        } else {
            return null;
        }
    }
}
