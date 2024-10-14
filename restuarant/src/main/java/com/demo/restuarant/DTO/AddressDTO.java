package com.demo.restuarant.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Long id;
    private String country;
    private String State;
    private String city;
    private String street;
    private String pincode;
    private String landMark;
}
