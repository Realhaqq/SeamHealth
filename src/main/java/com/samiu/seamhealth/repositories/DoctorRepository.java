package com.samiu.seamhealth.repositories;

import com.samiu.seamhealth.entities.Address;
import com.samiu.seamhealth.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Doctor findByEmail(String email);

    Doctor findByUuid(String uuId);
}
