package com.samiu.seamhealth.services;

import com.samiu.seamhealth.entities.Address;
import com.samiu.seamhealth.entities.Doctor;
import com.samiu.seamhealth.pojos.AddressUpdateRequest;
import com.samiu.seamhealth.pojos.ApiResponse;
import com.samiu.seamhealth.pojos.DoctorAddRequest;
import com.samiu.seamhealth.repositories.AddressRepository;
import com.samiu.seamhealth.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            return new ResponseEntity(new ApiResponse(false, "Doctor already exist", 101, null),
                    HttpStatus.BAD_REQUEST);

        // generate uuid
        String uuid = java.util.UUID.randomUUID().toString();

        // add doctor
        doctor = new Doctor();
        doctor.setFirstName(doctorAddRequest.getFirstName());
        doctor.setLastName(doctorAddRequest.getLastName());
        doctor.setEmail(doctorAddRequest.getEmail());
        doctor.setUuid(uuid);
        doctor.setPhone(doctorAddRequest.getPhone());
        doctor.setCreatedAt(new Date());

        // add address
        Address address = new Address();
        address.setHomeAddress(doctorAddRequest.getHomeAddress());
        address.setLga(doctorAddRequest.getLga());
        address.setState(doctorAddRequest.getState());
        address.setCreatedAt(new Date());

        doctor.setAddress(address);

        doctorRepository.save(doctor);

        return new ResponseEntity(new ApiResponse(true, "Doctor added successfully", 000, doctor),
                HttpStatus.OK);
    }

    public ResponseEntity<?> getAllDoctors() {
        return ResponseEntity.ok(doctorRepository.findAll());
    }

    public ResponseEntity<?> getDoctorById(String uuId) {
        Doctor doctor = doctorRepository.findByUuid(uuId);
        if (doctor == null)
            return new ResponseEntity(new ApiResponse(false, "Doctor does not exist", 101, null),
                    HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(doctor);
    }

    public ResponseEntity<?> updateDoctorAddress(String uuId, AddressUpdateRequest addressUpdateRequest) {
        Doctor doctor = doctorRepository.findByUuid(uuId);
        if (doctor == null)
            return new ResponseEntity(new ApiResponse(false, "Doctor does not exist", 101, null),
                    HttpStatus.BAD_REQUEST);

        Address address = addressRepository.findById(doctor.getAddress().getId()).get();
        address.setHomeAddress(addressUpdateRequest.getHomeAddress());
        address.setLga(addressUpdateRequest.getLga());
        address.setState(addressUpdateRequest.getState());
        address.setUpdatedAt(new Date());
        addressRepository.save(address);

        return new ResponseEntity(new ApiResponse(true, "Doctor address updated successfully", 000, address),
                HttpStatus.OK);
    }

    public ResponseEntity<?> deleteDoctor(String uuId) {
        Doctor doctor = doctorRepository.findByUuid(uuId);
        if (doctor == null)
            return new ResponseEntity(new ApiResponse(false, "Doctor does not exist", 101, null),
                HttpStatus.BAD_REQUEST);

        doctorRepository.delete(doctor);
        return new ResponseEntity(new ApiResponse(true, "Doctor deleted successfully", 000, null),
                HttpStatus.OK);
    }
}
