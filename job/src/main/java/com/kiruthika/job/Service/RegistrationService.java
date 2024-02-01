// package com.kiruthika.job.Service;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;



//     @Service
//     public class RegistrationService {

//         @Autowired
//         private UserDataRepository userDataRepository;

//         public void deleteUser(Long userId){
//             if (userDataRepository.existsById(userId)) {
//                 userDataRepository.deleteById(userId);
//             } else {
//                 throw new RuntimeException("User with ID " + userId + " not found");
//             }
//         }
//     }

