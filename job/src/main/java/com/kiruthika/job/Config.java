package com.kiruthika.job;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Config {
     @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/jobSeeker/**").authenticated()
                        .requestMatchers("/employer/**").authenticated()
                        .requestMatchers("/register/**").permitAll())
                        .httpBasic(Customizer.withDefaults());
    return http.build();
    
    }
    
//     @Bean
//     public UserDetailsService userDetailsService() {
        
//         UserDetails employer = User.builder().username("employer").password(passwordEncoder().encode("employer"))
//                 .build();
        
//         UserDetails jobseeker = User.builder().username("jobseeker").password(passwordEncoder().encode("jobseeker"))
//                 .build();
//         return new InMemoryUserDetailsManager(employer,jobseeker);
// }
}
