package com.demo.products.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping()
    public ResponseEntity<?> prods(){
        return ResponseEntity.status(HttpStatus.OK).body("Added prod successfully");
    }
}
