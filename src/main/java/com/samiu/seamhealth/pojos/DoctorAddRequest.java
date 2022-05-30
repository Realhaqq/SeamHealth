package com.samiu.seamhealth.pojos;

import lombok.Data;

@Data
public class DoctorAddRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String addressId;
    private String homeAddress;
    private String lga;
    private String state;
}
