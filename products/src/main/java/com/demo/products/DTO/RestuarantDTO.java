package com.demo.products.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestuarantDTO {
    private Long id;
    private String restuarantName;
    private Address address;
    private String phoneNumber;
    private double rating;
}
