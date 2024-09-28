package com.demo.products.Controller;

import com.demo.products.DAO.ProductDAO;
import com.demo.products.DTO.ProdResDTO;
import com.demo.products.DTO.ProductDTO;
import com.demo.products.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService prodService;

    @GetMapping()
    public ResponseEntity<?> prods(){
        List<ProdResDTO> productDTOList = prodService.getAllItems();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(productDTOList);
    }

    @PostMapping()
    public ResponseEntity<String> addItem(@ModelAttribute ProductDTO productDTO, @RequestParam("image")MultipartFile image) throws IOException {
       ProductDAO prod = prodService.addItem(productDTO,image);
       if(prod!=null){
           return new ResponseEntity<>("Product saved to DB Successfully!!!",HttpStatus.OK);
       }else{
           return new ResponseEntity<>("Something went Wrong",HttpStatus.BAD_REQUEST);
       }
    }


}
