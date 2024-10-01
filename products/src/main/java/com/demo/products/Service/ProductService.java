package com.demo.products.Service;

import com.demo.products.DAO.ProductDAO;
import com.demo.products.DTO.ProdResDTO;
import com.demo.products.DTO.ProductDTO;
import com.demo.products.Repository.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


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

    public ProductDAO updateProduct(Integer id, ProductDTO productDTO, MultipartFile image) throws IOException {
        ModelMapper mapper = new ModelMapper();
        Optional<ProductDAO> prodDao = productRepo.findById(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dt = LocalDateTime.now();
        if(prodDao.isPresent()){
            ProductDAO dao = prodDao.get();
            dao.setName(productDTO.getName());
            dao.setDescription(productDTO.getDescription());
            dao.setPrize(productDTO.getPrize());
            dao.setDiscount(productDTO.getDiscount());
            dao.setCategory(productDTO.getCategory());
            dao.setImage(image.getBytes());
            dao.setUpdatedOn(formatter.format(dt));
            return productRepo.save(dao);
        }else{
            return null;
        }

    }

    public ProductDAO deleteRecord(Integer id) {
        Optional<ProductDAO> res =productRepo.findById(id);
        if(res.isPresent()){
            productRepo.deleteById(id);
            return res.get();
        }else{
            return null;
        }

    }
}
