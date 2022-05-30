package com.samiu.seamhealth.services;

import com.samiu.seamhealth.entities.Address;
import com.samiu.seamhealth.entities.Doctor;
import com.samiu.seamhealth.pojos.AddressUpdateRequest;
import com.samiu.seamhealth.pojos.DoctorAddRequest;
import com.samiu.seamhealth.repositories.AddressRepository;
import com.samiu.seamhealth.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    AddressRepository addressRepository;

    public ResponseEntity<?> addDoctor(DoctorAddRequest doctorAddRequest) {
        // check if doctor already exists by email
        Doctor doctor = doctorRepository.findByEmail(doctorAddRequest.getEmail());
        if (doctor != null)
            return ResponseEntity.badRequest().body("Doctor already exists");


        // add address
        Address address = new Address();
        address.setHomeAddress(doctorAddRequest.getHomeAddress());
        address.setLga(doctorAddRequest.getLga());
        address.setState(doctorAddRequest.getState());
        address.setCreatedAt(new Date());
        addressRepository.save(address);


        // generate uuid
        String uuid = java.util.UUID.randomUUID().toString();

        // add doctor
        doctor.setFirstName(doctorAddRequest.getFirstName());
        doctor.setLastName(doctorAddRequest.getLastName());
        doctor.setEmail(doctorAddRequest.getEmail());
        doctor.setUuid(uuid);
        doctor.setPhone(doctorAddRequest.getPhone());
        doctor.setAddressId(address.getId());
        doctor.setCreatedAt(new Date());
        doctorRepository.save(doctor);

        return ResponseEntity.ok("Doctor added successfully");
    }

    public ResponseEntity<?> getAllDoctors() {
        return ResponseEntity.ok(doctorRepository.findAll());
    }

    public ResponseEntity<?> getDoctorById(String uuId) {
        Doctor doctor = doctorRepository.findByUuid(uuId);
        if (doctor == null)
            return ResponseEntity.badRequest().body("Doctor does not exist");
        return ResponseEntity.ok(doctor);
    }

    public ResponseEntity<?> updateDoctorAddress(String uuId, AddressUpdateRequest addressUpdateRequest) {
        Doctor doctor = doctorRepository.findByUuid(uuId);
        if (doctor == null)
            return ResponseEntity.badRequest().body("Doctor does not exist");

        Address address = addressRepository.findById(doctor.getAddressId()).get();
        address.setHomeAddress(addressUpdateRequest.getHomeAddress());
        address.setLga(addressUpdateRequest.getLga());
        address.setState(addressUpdateRequest.getState());
        address.setUpdatedAt(new Date());
        addressRepository.save(address);

        return ResponseEntity.ok("Doctor address updated successfully");
    }

    public ResponseEntity<?> deleteDoctor(String uuId) {
        Doctor doctor = doctorRepository.findByUuid(uuId);
        if (doctor == null)
            return ResponseEntity.badRequest().body("Doctor does not exist");

        doctorRepository.delete(doctor);
        return ResponseEntity.ok("Doctor deleted successfully");
    }
}
