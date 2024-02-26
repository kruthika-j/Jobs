package com.kiruthika.job.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;

import com.kiruthika.job.Entity.Employer;
import com.kiruthika.job.Entity.JobSeeker;
import com.kiruthika.job.Repository.EmployerRepository;
import com.kiruthika.job.Repository.JobSeekerRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class EmployerSeekerDetailsService implements UserDetailsService {
        @Autowired
        private EmployerRepository employerRepository;

        @Autowired
        private JobSeekerRepository jobSeekerRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                                .currentRequestAttributes()).getRequest();

                String url = request.getRequestURL().toString();

                if (url.contains("employer")) {
                        Employer employer = Optional.ofNullable(employerRepository.findByUname(username))
                                        .orElse(null);

                        if (employer != null) {

                                return User.builder()
                                                .username(employer.getUname())
                                                .password(employer.getPassword())
                                                .passwordEncoder(passwordEncoder::encode)
                                                .roles("EMPLOYER")
                                                .build();
                        }
                } else if (url.contains("jobSeeker")) {
                        JobSeeker jobSeeker = Optional.ofNullable(jobSeekerRepository.findByjuname(username))
                                        .orElseThrow(() -> new UsernameNotFoundException(
                                                        "JobSeeker not found with username: " + username));
                        return User.builder()
                                        .username(jobSeeker.getJuname())
                                        .password(jobSeeker.getPassword())
                                        .passwordEncoder(passwordEncoder::encode)
                                        .roles("JOB_SEEKER")
                                        .build();
                }
                throw new UsernameNotFoundException("User not found with username: " + username);
        }
}