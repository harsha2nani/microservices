package com.demo.products.DAO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
@Schema(description = "Product Entity")
public class ProductDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Schema(description = "Unique id for product entity")
    private Integer id;

    @Column(name = "name")
    @Schema(description = "name of product")
    private String name;

    @Column(name = "description")
    @Schema(description = "product description")
    private String description;

    @Column(name="prize")
    @Schema(description = "Prize of the product")
    private Double prize;

    @Column(name="discount")
    @Schema(description = "discount of the product")
    private Integer discount;

    @Column(name="category")
    @Schema(description = "Category of the product")
    private String category;

    @Column(name = "image")
    @Lob
    @Schema(description = "Image / file of the product")
    private byte[] image;

    @Column(name="createdOn")
    @Schema(description = "created on date for the product")
    private String createdOn;

    @Column(name = "updatedOn")
    @Schema(description = "updated on date for the product")
    private String updatedOn;


}
