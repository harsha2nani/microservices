package com.demo.products.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdResDTO {
    private Integer id;
    private String name;
    private String description;
    private Double prize;
    private Integer discount;
    private String category;
    private String image;
    private String createdOn;
    private String updatedOn;
}
