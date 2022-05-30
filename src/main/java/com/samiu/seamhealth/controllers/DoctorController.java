package com.samiu.seamhealth.controllers;

import com.samiu.seamhealth.pojos.AddressUpdateRequest;
import com.samiu.seamhealth.pojos.DoctorAddRequest;
import com.samiu.seamhealth.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping("/doctor/add")
    public ResponseEntity<?> addDoctor(@Valid @RequestBody DoctorAddRequest doctorAddRequest) {
        return doctorService.addDoctor(doctorAddRequest);
    }

    @GetMapping("/doctor/all")
    public ResponseEntity<?> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/doctor/{uuId}")
    public ResponseEntity<?> getDoctorById(@PathVariable String uuId) {
        return doctorService.getDoctorById(uuId);
    }

    @PutMapping("/doctor/{uuId}/address")
    public ResponseEntity<?> updateDoctorAddress(@PathVariable String uuId, @RequestBody AddressUpdateRequest addressUpdateRequest) {
        return doctorService.updateDoctorAddress(uuId, addressUpdateRequest);
    }

    @DeleteMapping("/doctor/{uuId}")
    public ResponseEntity<?> deleteDoctor(@PathVariable String uuId) {
        return doctorService.deleteDoctor(uuId);
    }

}
