package com.demo.restuarant.DTO;

import com.demo.restuarant.Model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestuarantDTO {
    private Long id;
    private String restuarantName;
    private Address address;
    private String phoneNumber;
    private double rating;
}
