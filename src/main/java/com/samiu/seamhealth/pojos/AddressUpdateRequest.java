package com.samiu.seamhealth.pojos;

import lombok.Data;

@Data
public class AddressUpdateRequest {
    private String homeAddress;
    private String lga;
    private String state;
}
