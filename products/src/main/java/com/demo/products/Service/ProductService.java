package com.demo.products.Service;

import com.demo.products.DAO.ProductDAO;
import com.demo.products.DTO.ProdResDTO;
import com.demo.products.DTO.ProductDTO;
import com.demo.products.Repository.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<ProdResDTO> getAllItems() {
     List<ProductDAO> prodList = productRepo.findAll();
        return prodList.stream().map(productDAO -> ProdResDTO.builder().id(productDAO.getId())
                .name(productDAO.getName())
                .prize(productDAO.getPrize())
                .description(productDAO.getDescription())
                .discount(productDAO.getDiscount())
                .category(productDAO.getCategory())
                .image(Base64.getEncoder().encodeToString(productDAO.getImage()))
                .createdOn(productDAO.getCreatedOn())
                .updatedOn(productDAO.getUpdatedOn())
                .build()).toList();
    }

    public ProductDAO addItem(ProductDTO productDTO, MultipartFile image) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dt = LocalDateTime.now();
        ModelMapper mapper = new ModelMapper();
        ProductDAO prod = mapper.map(productDTO,ProductDAO.class);
        prod.setImage(image.getBytes());
        prod.setCreatedOn(formatter.format(dt));
        prod.setUpdatedOn(formatter.format(dt));
        return productRepo.save(prod);
    }
}
