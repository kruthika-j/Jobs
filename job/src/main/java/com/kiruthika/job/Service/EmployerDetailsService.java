package com.kiruthika.job.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kiruthika.job.Entity.Employer;
import com.kiruthika.job.Entity.JobSeeker;
import com.kiruthika.job.Repository.EmployerRepository;
import com.kiruthika.job.Repository.JobSeekerRepository;

@Service
public class EmployerDetailsService implements UserDetailsService {
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employer employer = Optional.ofNullable(employerRepository.findByUname(username))
                .orElse(null);

        if (employer != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(employer.getUname())
                    .password(employer.getPassword())
                    .passwordEncoder(passwordEncoder::encode)
                    .roles("EMPLOYER")
                    .build();
        }

        JobSeeker jobSeeker = Optional.ofNullable(jobSeekerRepository.findByjuname(username))
                .orElseThrow(() -> new UsernameNotFoundException("JobSeeker not found with username: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(jobSeeker.getJuname())
                .password(jobSeeker.getPassword())
                .passwordEncoder(passwordEncoder::encode)
                .roles("JOB_SEEKER")
                .build();
    }
}
