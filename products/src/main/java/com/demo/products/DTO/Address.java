package com.demo.products.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private Long id;
    private String country;
    private String state;
    private String city;
    private String street;
    private String pincode;
    private String landMark;
}
