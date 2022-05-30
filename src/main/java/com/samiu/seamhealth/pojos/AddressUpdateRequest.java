package com.samiu.seamhealth.pojos;

import lombok.Data;

@Data
public class AddressUpdateRequest {
    String homeAddress;
    String lga;
    String state;
}
