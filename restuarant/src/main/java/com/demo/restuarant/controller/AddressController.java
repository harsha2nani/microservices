package com.demo.restuarant.controller;

import com.demo.restuarant.DTO.AddressDTO;
import com.demo.restuarant.Model.Address;
import com.demo.restuarant.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/getAll")
    public ResponseEntity<List<AddressDTO>> fetchAddress(){
        List<AddressDTO> addressDTOList =addressService.fetchAddress();
        return new ResponseEntity<>(addressDTOList,HttpStatus.OK);
    }

    @PostMapping("/addAddress")
    public ResponseEntity<?> addAddress(@RequestBody Address address){
       Address res = addressService.createAddress(address);
       return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> fetchById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getAddressById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long id,@RequestBody Address address){
       return ResponseEntity.status(200).body(addressService.updateAddress(id,address));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id){
        return ResponseEntity.status(200).body(addressService.deleteAddress(id));
    }
}
