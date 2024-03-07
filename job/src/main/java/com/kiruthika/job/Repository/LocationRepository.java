package com.kiruthika.job.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiruthika.job.Entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    Location findByStateName(String stateName);
}
