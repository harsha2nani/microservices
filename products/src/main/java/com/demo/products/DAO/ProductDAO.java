package com.demo.products.DAO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProductDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name="prize")
    private Double prize;

    @Column(name="discount")
    private Integer discount;

    @Column(name="category")
    private String category;

    @Column(name = "image")
    @Lob
    private byte[] image;

    @Column(name="createdOn")
    private String createdOn;

    @Column(name = "updatedOn")
    private String updatedOn;


}
